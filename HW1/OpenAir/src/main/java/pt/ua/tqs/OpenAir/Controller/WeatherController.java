package pt.ua.tqs.OpenAir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.OpenAir.Service.WeatherService;


@RestController
public class WeatherController {
    
    @Autowired
    WeatherService weatherService;


    

}
