package pt.ua.tqs.openair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pt.ua.tqs.openair.data.model.Coords;
import pt.ua.tqs.openair.data.model.Location;
import pt.ua.tqs.openair.data.model.Stats;

@Service
public class WeatherService {

    @Autowired
    CacheService cacheService;

    @Autowired
    RestTemplate restTemplate;

    public Location getWeather(String local) {

        Location location = cacheService.getLocation(local);

        if (locationPresentInCache(location)) {
            return location;
        } else {

            Object obf = restTE

            Coords coords = new Coords(0, 0);

            location = new Location(local, coords, new Stats(0, 0, 0, 0));

            cacheService.postLocation(location);

            return location;

        }

    }

    private boolean locationPresentInCache(Location location) {
        return location != null;
    }

}
