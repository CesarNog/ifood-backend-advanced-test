package br.com.ifood.advancedtest.spotify.service;


import br.com.ifood.advancedtest.spotify.domain.TrackList;
import br.com.ifood.advancedtest.spotify.exception.BusinessException;
import br.com.ifood.advancedtest.spotify.exception.CityNotFoundException;
import br.com.ifood.advancedtest.spotify.exception.CoordinatesNotFoundException;

public interface TrackAdvisorService {
	/**
	 * Advising a list of tracks based on a city name
	 *
	 * @param cityName the city name
	 * @return a list of {@link TrackList}s with the advised tracks
	 * @throws BusinessException whenever a business error occurs
	 * @throws CityNotFoundException whenever the city could not be found
	 */
	TrackList adviseTracksByCityName(String cityName) throws BusinessException, CityNotFoundException;

	/**
	 * Advising a list of tracks based on a lat/long coordinate
	 *
	 * @param latitude the latitude of the city
	 * @param longitude the longitude of the city
	 * @return a list of {@link TrackList}s with the advised tracks
	 * @throws BusinessException whenever a business error occurs
	 * @throws CoordinatesNotFoundException whenever the request values are no valid
	 */
	TrackList adviseTracksByLocation(Integer latitude, Integer longitude) throws BusinessException, CoordinatesNotFoundException;
}
