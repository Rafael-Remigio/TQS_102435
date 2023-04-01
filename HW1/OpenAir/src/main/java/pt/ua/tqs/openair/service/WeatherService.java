package pt.ua.tqs.openair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pt.ua.tqs.openair.data.DTO.GeocodingDTO;
import pt.ua.tqs.openair.data.DTO.Northeast;
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

            Coords coords = getCoords(local);

            location = new Location(local, coords, new Stats(0, 0, 0, 0));

            cacheService.postLocation(location);

            return location;

        }

    }

    private Coords getCoords(String local) {
        String url = "https://api.opencagedata.com/geocode/v1/json?q="+ local +"&key=c90656c4111e481dbaa7c152f5361142&pretty=1&no_annotations=1&limit=1";

        GeocodingDTO geocodingObj = restTemplate.getForObject(url, GeocodingDTO.class);
 
        Northeast APIcoords = geocodingObj.results.get(0).bounds.northeast;

        Coords coords = new Coords(APIcoords.lat, APIcoords.lng);
        return coords;
    }

    private boolean locationPresentInCache(Location location) {
        return location != null;
    }

}
