package pt.ua.tqs.openair.service;
import org.springframework.stereotype.Service;

import pt.ua.tqs.openair.data.model.Coords;
import pt.ua.tqs.openair.data.model.Location;
import pt.ua.tqs.openair.data.model.Stats;

@Service
public class WeatherService {
    

    public Location getWeather(String local){

        return new Location(local,new Coords(0,0),new Stats(0, 0, 0, 0));
    }

}
