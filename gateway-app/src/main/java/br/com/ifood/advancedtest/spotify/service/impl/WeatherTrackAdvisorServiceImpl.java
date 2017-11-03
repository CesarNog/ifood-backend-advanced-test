package br.com.ifood.advancedtest.spotify.service.impl;

import br.com.ifood.advancedtest.spotify.domain.TrackList;
import br.com.ifood.advancedtest.spotify.enums.TrackGenre;
import br.com.ifood.advancedtest.spotify.exception.BusinessException;
import br.com.ifood.advancedtest.spotify.exception.CityNotFoundException;
import br.com.ifood.advancedtest.spotify.exception.CoordinatesNotFoundException;
import br.com.ifood.advancedtest.spotify.exception.NetworkException;
import br.com.ifood.advancedtest.spotify.network.TrackListNetwork;
import br.com.ifood.advancedtest.spotify.network.WeatherForecastNetwork;
import br.com.ifood.advancedtest.spotify.service.TrackAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherTrackAdvisorServiceImpl implements TrackAdvisorService {
	@Autowired
	private WeatherForecastNetwork weatherNetwork;

	@Autowired
	private TrackListNetwork trackListNetwork;

	// defining the ranges
	private static final float HIGH_TEMPERATURE = 30.0f;
	private static final float MAXIMUM_COOL_TEMPERATURE = 30.0f;
	private static final float MINIMUM_COOL_TEMPERATURE = 15.0f;
	private static final float MAXIMUM_CHILL_TEMPERATURE = 14.0f;
	private static final float MINIMUM_CHILL_TEMPERATURE = 10.0f;

	// the values of latitude can vary between 90 and -90
	private static final int MINIMUM_LATITUDE_VALUE = -90;
	private static final int MAXIMUM_LATITUDE_VALUE = 90;

	// the values of longitude can vary between 180 and -180
	private static final int MINIMUM_LONGITUDE_VALUE = -180;
	private static final int MAXIMUM_LONGITUDE_VALUE = 180;


	@Override
	public TrackList adviseTracksByCityName(String cityName) throws BusinessException, CityNotFoundException {
		TrackList tracks = null;
		try {
			// getting the temperature from the coordination
			Float temperature = weatherNetwork.getTemperatureFromCity(cityName);

			// getting the genre based on the temperature
			TrackGenre genre = getGenreBasedOnTemperature(temperature);

			// getting the tracks from that genre
			tracks = trackListNetwork.suggestTracksForGenre(genre);
		} catch (NetworkException nex) {
			throw new BusinessException(nex);
		}
		return tracks;
	}

	@Override
	public TrackList adviseTracksByLocation(Integer latitude, Integer longitude)
			throws BusinessException, CoordinatesNotFoundException {
		TrackList tracks = null;

		// first, we must check if the coordinates are valid
		checkCoordinates(latitude, longitude);

		try {
			// getting the temperature from the coordination
			Float temperature = weatherNetwork.getTemperatureFromLocation(latitude, longitude);

			// getting the genre based on the temperature
			TrackGenre genre = getGenreBasedOnTemperature(temperature);

			// getting the tracks from that genre
			tracks = trackListNetwork.suggestTracksForGenre(genre);
		} catch (NetworkException nex) {

		    
			throw new BusinessException(nex);
		}
		return tracks;
	}

	/**
	 * Getting the track genre based on the temperature
	 * <p>
	 * There are four possible genres to be chosen and each one of them are bing to
	 * a given temperature range
	 * <p>
	 * If temperature (celsius) is above 30 degrees, it returns party music genre
	 * </br>
	 * In case temperature is above 15 and below 30 degrees, it returns pop music
	 * genre </br>
	 * If it's a bit chilly (between 10 and 14 degrees), it returns rock music genre
	 * </br>
	 * Otherwise (below 10), if it's freezing outside, and it returns classical
	 * music genre </br>
	 *
	 * @param temperature the current temperature
	 * @return a {@link TrackGenre} with the track genre
	 */
	private TrackGenre getGenreBasedOnTemperature(float temperature) {
		TrackGenre genre = null;
		if (temperature > HIGH_TEMPERATURE) {
			genre = TrackGenre.PARTY;
		} else if (temperature <= MAXIMUM_COOL_TEMPERATURE && temperature >= MINIMUM_COOL_TEMPERATURE) {
			genre = TrackGenre.POP;
		} else if (temperature <= MAXIMUM_CHILL_TEMPERATURE && temperature >= MINIMUM_CHILL_TEMPERATURE) {
			genre = TrackGenre.ROCK;
		} else {
			genre = TrackGenre.CLASSICAL;
		}
		return genre;
	}

	/**
	 * Checking if the coordinates are valid
	 * <p>
	 * To be considered valid, a latitude coordinate must be between -90 and 90
	 * </br>
	 * To be considered valid, a latitude coordinate must be between -180 and 180
	 *
	 * @param latitude the latitude to be checked
	 * @param longitude the longitude to be checked
	 * @throws CoordinatesNotFoundException whenever the latitude or the longitude
	 *            values are not valid
	 */
	private void checkCoordinates(int latitude, int longitude) throws CoordinatesNotFoundException {
		if (latitude <= MINIMUM_LATITUDE_VALUE || latitude >= MAXIMUM_LATITUDE_VALUE) {
			throw new CoordinatesNotFoundException("Invalid latitude");
		}
		if (longitude <= MINIMUM_LONGITUDE_VALUE || longitude >= MAXIMUM_LONGITUDE_VALUE) {
			throw new CoordinatesNotFoundException("Invalid longitude");
		}
	}
}
