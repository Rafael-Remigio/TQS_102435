package pt.ua.tqs.openair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pt.ua.tqs.openair.data.DTO.GeoCoding.GeocodingDTO;
import pt.ua.tqs.openair.data.DTO.GeoCoding.Northeast;
import pt.ua.tqs.openair.data.DTO.OpenWeather.AirQualityDTO;
import pt.ua.tqs.openair.data.DTO.OpenWeather.Components;
import pt.ua.tqs.openair.data.model.Coords;
import pt.ua.tqs.openair.data.model.Location;
import pt.ua.tqs.openair.data.model.Stats;

@Service
public class WeatherService {

    @Autowired
    CacheService cacheService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${app.geocodingUrl}")
    private String geoCodingUrl;

    @Value("${app.geocodingApiKey}")
    private String geoCodingApiKey;

    @Value("${app.openWeatherUrl}")
    private String openWeatherUrl;

    @Value("${app.openWeatherApiKey}")
    private String openWeatherApiKey;


    public Location getWeather(String local) {

        Location location = cacheService.getLocation(local);

        if (locationPresentInCache(location)) {
            return location;
        } else {

            Coords coords = getLocationCoords(local);

            Stats stats = getAirQualityStats(coords);

            location = new Location(local, coords, stats);

            cacheService.postLocation(location);

            return location;

        }

    }

    private Stats getAirQualityStats(Coords coords) {
        String url = openWeatherUrl + "lat="+coords.getLat()+"&lon="+coords.getLgn()+"&appid="+openWeatherApiKey;

        AirQualityDTO airQualityDTO = restTemplate.getForObject(url, AirQualityDTO.class);

        Components components = airQualityDTO.list.get(0).components;
        
        Stats stats = new Stats(components.co, components.no2, components.pm2_5, components.pm10);
        return stats;
    }

    private Coords getLocationCoords(String local) {
        
        String url = geoCodingUrl + local +"&key="+ geoCodingApiKey +"&pretty=1&no_annotations=1&limit=1";

        GeocodingDTO geocodingObj = restTemplate.getForObject(url, GeocodingDTO.class);
 
        Northeast APIcoords = geocodingObj.results.get(0).bounds.northeast;

        Coords coords = new Coords(APIcoords.lat, APIcoords.lng);
        return coords;
    }

    private boolean locationPresentInCache(Location location) {
        return location != null;
    }

}
