package pt.ua.tqs.openair.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pt.ua.tqs.openair.data.dto.geocoding.GeocodingDTO;
import pt.ua.tqs.openair.data.dto.geocoding.Northeast;
import pt.ua.tqs.openair.data.dto.openweather.AirQualityDTO;
import pt.ua.tqs.openair.data.dto.openweather.Components;
import pt.ua.tqs.openair.data.model.Coords;
import pt.ua.tqs.openair.data.model.Location;
import pt.ua.tqs.openair.data.model.Stats;

@Service
public class WeatherService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

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

            if (coords == null) {

                LOGGER.error("GeoLocation API did not respond");

                return null;
            }

            Stats stats = getAirQualityStats(coords);

            if (stats == null) {

                LOGGER.error("OpenWeather Air Quality API did not respond");

                return null;
            }

            location = new Location(local, coords, stats);

            cacheService.postLocation(location);

            return location;

        }

    }

    private Stats getAirQualityStats(Coords coords) {
        String url = openWeatherUrl + "lat=" + coords.getLat() + "&lon=" + coords.getLgn() + "&appid="
                + openWeatherApiKey;
        try {
            AirQualityDTO airQualityDTO = restTemplate.getForObject(url, AirQualityDTO.class);
            if (airQualityDTO == null) {
                return null;
            }

            Components components = airQualityDTO.list[0].components;

            Stats stats = new Stats(components.co, components.no2, components.pm2_5, components.pm10);
            return stats;
        } catch (Exception e) {
            return null;
        }

    }

    public Coords getLocationCoords(String local) {

        String url = geoCodingUrl + local + "&key=" + geoCodingApiKey + "&pretty=1&no_annotations=1&limit=1";
        try {
        GeocodingDTO geocodingObj = restTemplate.getForObject(url, GeocodingDTO.class);

        if (geocodingObj == null) {
            return null;
        }

        Northeast APIcoords = geocodingObj.results.get(0).bounds.northeast;

        Coords coords = new Coords(APIcoords.lat, APIcoords.lng);
        return coords;
        }
        catch (Exception e) {
            return null;
        }
    }

    private boolean locationPresentInCache(Location location) {
        return location != null;
    }

}
