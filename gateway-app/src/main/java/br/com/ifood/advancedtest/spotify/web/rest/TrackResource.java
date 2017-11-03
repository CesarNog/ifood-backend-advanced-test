package br.com.ifood.advancedtest.spotify.web.rest;

import javax.validation.Valid;

import br.com.ifood.advancedtest.spotify.domain.ServerError;
import br.com.ifood.advancedtest.spotify.domain.TrackList;
import br.com.ifood.advancedtest.spotify.exception.*;
import br.com.ifood.advancedtest.spotify.service.TrackAdvisorService;
import br.com.ifood.advancedtest.spotify.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/suggestions")
public class TrackResource {
    @Autowired
    private TrackAdvisorService trackAdvisorService;

    /**
     * Add the audiences in a program
     * <p>
     * (200) In case everything was OK, it returns a {@link TrackList} object with
     * the tracks </br>
     * (400) In case anything was wrong with the request </br>
     * (404) In case the location was not found </br>
     * (500) In case of an Internal Server Error
     *
     * @param city the name of the city to get the tracks based on the location
     * @returns a {@link ResponseEntity} object wrapping the response result
     */
    @RequestMapping(method = RequestMethod.GET, value = "/city/{city}")
    public ResponseEntity<?> getTracksBasedByCityName(@PathVariable @Valid String city) {
        ResponseEntity<?> response = null;
        try {
            checkCityName(city);

            // getting the tracks based on the city name
            TrackList tracks = trackAdvisorService.adviseTracksByCityName(city);

            // filling the response object
            response = new ResponseEntity<>(tracks, HttpStatus.OK);
        } catch (BusinessException bex) {
            response = new ResponseEntity<>(new ServerError(bex), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CityNotFoundException cnfe) {
            response = new ResponseEntity<>(new ServerError(cnfe), HttpStatus.NOT_FOUND);
        } catch (InvalidCityException icex) {
            response = new ResponseEntity<>(new ServerError(icex), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Add the audiences in a program
     * <p>
     * (200) In case everything was OK, it returns a {@link TrackList} object with
     * the tracks </br>
     * (400) In case anything was wrong with the request </br>
     * (404) In case the location was not found </br>
     * (500) In case of an Internal Server Error
     *
     * @returns a {@link ResponseEntity} object wrapping the response result
     */
    @RequestMapping(method = RequestMethod.GET, value = "/location")
    public ResponseEntity<?> getTracksBasedByCoordinates(@RequestParam @Valid Integer lat,
                                                         @RequestParam @Valid Integer lon) {
        ResponseEntity<?> response = null;
        try {
            checkCoordinates(lat, lon);

            // getting the tracks based on the location
            TrackList tracks = trackAdvisorService.adviseTracksByLocation(lat, lon);

            // filling the response object
            response = new ResponseEntity<>(tracks, HttpStatus.OK);
        } catch (BusinessException bex) {
            response = new ResponseEntity<>(new ServerError(bex), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CoordinatesNotFoundException cnfe) {
            response = new ResponseEntity<>(new ServerError(cnfe), HttpStatus.NOT_FOUND);
        } catch (InvalidCoordinatesException icex) {
            response = new ResponseEntity<>(new ServerError(icex), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Checking if the coordinates are valid
     *
     * @param lat the latitude
     * @param lon the longitude
     * @throws InvalidCoordinatesException whenever either of the cooridnates are
     *            not valid
     */
    private void checkCoordinates(Integer lat, Integer lon) throws InvalidCoordinatesException {
        if (lat == null) {
            throw new InvalidCoordinatesException("The latitude (lat) could not be null nor empty");
        }
        if (lon == null) {
            throw new InvalidCoordinatesException("The longitue (lon) could not be null nor empty");
        }
    }

    /**
     * Checking if the city name is valid
     *
     * @param city the city name
     * @throws InvalidCityException whenever the city name was not valid
     */
    private void checkCityName(String city) throws InvalidCityException {
        if (!StringUtils.isValid(city)) {
            throw new InvalidCityException("The city could not be null nor empty");
        }
    }
}
