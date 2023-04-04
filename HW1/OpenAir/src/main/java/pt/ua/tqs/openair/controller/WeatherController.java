package pt.ua.tqs.openair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.openair.data.model.*;
import pt.ua.tqs.openair.service.WeatherService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class WeatherController {


	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);



    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public Location getWeather(
        @RequestParam(value = "local", defaultValue = "", required = false) String localString
    ){
        try {

            LOGGER.debug("Received a weather request for" + localString);
            Location location = weatherService.getWeather(localString);

            if (location == null){
                throw new LocationNotFoundException("No location found for" + localString);
            }

            return location;
            
        } 
        catch (LocationNotFoundException exc) {
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "Location or Address Not Found", exc);
        }

    }
    

}

