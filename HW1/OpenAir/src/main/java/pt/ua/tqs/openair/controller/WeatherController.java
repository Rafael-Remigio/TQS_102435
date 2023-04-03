package pt.ua.tqs.openair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.openair.data.model.*;
import pt.ua.tqs.openair.service.WeatherService;

import org.springframework.web.bind.annotation.*;


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
        LOGGER.info("Received a weather request");
        return weatherService.getWeather(localString);
    }
    

}
