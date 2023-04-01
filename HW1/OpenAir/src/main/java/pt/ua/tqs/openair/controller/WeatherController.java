package pt.ua.tqs.openair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.openair.data.model.*;
import pt.ua.tqs.openair.service.WeatherService;

import org.springframework.web.bind.annotation.*;


@RestController
public class WeatherController {
    
    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public Location getWeather(
        @RequestParam(value = "local", defaultValue = "", required = false) String localString
    ){
        return weatherService.getWeather(localString);
    }
    

}
