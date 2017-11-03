package br.com.ifood.advancedtest.spotify.network.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ifood.advancedtest.spotify.constants.HTTPConstants;
import br.com.ifood.advancedtest.spotify.domain.TrackDescription;
import br.com.ifood.advancedtest.spotify.domain.TrackList;
import br.com.ifood.advancedtest.spotify.domain.spotify.Artist;
import br.com.ifood.advancedtest.spotify.domain.spotify.AuthenticationToken;
import br.com.ifood.advancedtest.spotify.domain.spotify.Tracks;
import br.com.ifood.advancedtest.spotify.enums.TrackGenre;
import br.com.ifood.advancedtest.spotify.exception.NetworkException;
import br.com.ifood.advancedtest.spotify.network.TrackListNetwork;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class SpotifyTrackListNetwork implements TrackListNetwork {
	@Value("${spotify.client_id}")
	private String clientID;

	@Value("${spotify.client_secret}")
	private String clientSecret;

	@Autowired
	private RestTemplate restTemplate;

	private static final String GRANT_TYPE = "grant_type";
	private static final String CLIENT_CREDENTIALS = "client_credentials";
	private static final String AUTHENTICATION_API_URL = "https://accounts.spotify.com/api/token";
	private static final String RECOMMENDATION_API_URL = "https://api.spotify.com/v1/recommendations?seed_genres=%s";

	@Override
	public TrackList suggestTracksForGenre(TrackGenre genre) throws NetworkException {
		Tracks suggestedTracks = suggestTracksForGenre(genre.description());

		// parsing the result
		List<TrackDescription> descriptions = suggestedTracks.getTracks().stream()
				.map(t -> new TrackDescription(
						t.getArtists().stream().findFirst().orElse(new Artist()).getName(),
						t.getAlbum().getName(),
						t.getName()))
				.collect(Collectors.toList());

		return new TrackList(genre, descriptions);
	}

    /**
     * Building the authentication key in order to perform new quests
     * <p>
     * Since the Spotify open API changes its authentication token from time to
     * time, we cannot store this authentication key in a static way.
     *
     * @return a String with the authentication key
     * @throws Exception whenever something went wrong while calling the Spotify API
     */
    private String getApiKey() throws Exception {
        // composing the authentication key by joining the client id with its secret
        String encodedToken = new String(Base64.encodeBase64((clientID + ":" + clientSecret).getBytes()));

        // building the request body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add(GRANT_TYPE, CLIENT_CREDENTIALS);

        // building the request header
        MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
        header.add(HTTPConstants.AUTHORIZATION_HEADER, HTTPConstants.BASIC_AUTHORIZATION + encodedToken);
        header.add(HTTPConstants.CONTENT_TYPE_HEADER, HTTPConstants.XFORM_ENCODE_TYPE);

        // building the request
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(body, header);

        // performing the request
        return restTemplate.exchange(AUTHENTICATION_API_URL, HttpMethod.POST, entity, AuthenticationToken.class).getBody()
            .getAccessToken();
    }


	/**
	 * Suggest a list of tracks based on a given genre
	 *
	 * @param genre the track's genre
	 * @return a {@link TrackDescription} object with the tracks
	 * @throws NetworkException whenever something went wrong while calling the Spotify API
	 */
	public Tracks suggestTracksForGenre(String genre) throws NetworkException {
		Tracks tracks = null;
		try {
			// building the URI
			final String uri = String.format(RECOMMENDATION_API_URL, genre);

			// building the request header
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add(HTTPConstants.AUTHORIZATION_HEADER, HTTPConstants.BEARER_AUTHORIZATION + getApiKey());
			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			// performing the request
			tracks = restTemplate.exchange(uri, HttpMethod.GET, entity, Tracks.class).getBody();
		} catch (Exception ex) {
			throw new NetworkException(ex);
		}
		return tracks;
	}
}
