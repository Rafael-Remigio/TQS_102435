package pt.ua.tqs.openair;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pt.ua.tqs.openair.data.dto.geocoding.Bounds;
import pt.ua.tqs.openair.data.dto.geocoding.GeocodingDTO;
import pt.ua.tqs.openair.data.dto.geocoding.Northeast;
import pt.ua.tqs.openair.data.dto.geocoding.Result;
import pt.ua.tqs.openair.data.dto.openMeto.Hourly;
import pt.ua.tqs.openair.data.dto.openMeto.OpenMetoStats;
import pt.ua.tqs.openair.data.dto.openweather.AirQualityDTO;
import pt.ua.tqs.openair.data.dto.openweather.Components;
import pt.ua.tqs.openair.data.dto.openweather.List;
import pt.ua.tqs.openair.data.model.Coords;
import pt.ua.tqs.openair.data.model.Location;
import pt.ua.tqs.openair.data.model.Stats;
import pt.ua.tqs.openair.service.CacheService;
import pt.ua.tqs.openair.service.WeatherService;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTests {
    
    @Mock
    private CacheService cacheService;

    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private WeatherService weatherService;


    @Value("${app.openWeatherUrl}")
    private String openWeatherUrl;

    @Value("${app.openWeatherApiKey}")
    private String openWeatherApiKey;


    @Value("${app.geocodingUrl}")
    private String baseGeoCodingUrl;

    @Value("${app.airQualityOpenMeteo}")
    private String baseOpenMetoUrl;

    @Value("${app.geocodingApiKey}")
    private String geoCodingApiKey;



    @Test
    @DisplayName("Test location is present in cache")
    void testGetWeather_LocationPresentInCache() {
        // given
        String local = "London";
        Location location = new Location(local, new Coords(0.0, 0.0), new Stats(0.0, 0.0, 0.0, 0.0));
        Mockito.when(cacheService.getLocation(local)).thenReturn(location);

        // when
        Location result = weatherService.getWeather(local);

        // then
        Assertions.assertEquals(location, result);
    }


    @Test
    @DisplayName("Test location is not in cache, first AirQuality API responds")
    void testGetWeather_LocationNotPresentInCache_Success() {
        // given
        String local = "London";
        Mockito.when(cacheService.getLocation(local)).thenReturn(null);


        String geocodingUrl = baseGeoCodingUrl + local + "&key=" + geoCodingApiKey + "&pretty=1&no_annotations=1&limit=1";

        GeocodingDTO geocodingDTO = new GeocodingDTO();

        Result resultDTO = new Result();
        resultDTO.bounds = new Bounds();
        resultDTO.bounds.northeast = new Northeast(50,50);
        geocodingDTO.results = new ArrayList<Result>(); 
        geocodingDTO.results.add(resultDTO);

        Mockito.when(restTemplate.getForObject(geocodingUrl, GeocodingDTO.class))
                .thenReturn(geocodingDTO);

        Coords coords = new Coords(50, 50);

        String AirQualityUrl = openWeatherUrl + "lat=" + coords.getLat() + "&lon=" + coords.getLgn() + "&appid="
                + openWeatherApiKey;

        AirQualityDTO airQualityDTO = new AirQualityDTO();
        List[] array = {new List()};
        airQualityDTO.list = array;
        airQualityDTO.list[0].components = new Components(1.0,2.0,3.0,4.0,5.0,6.0,8.0,9.0);
        
        Mockito.when(restTemplate.getForObject(AirQualityUrl, AirQualityDTO.class))
                .thenReturn(airQualityDTO);

        // when
        Location result = weatherService.getWeather(local);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(local, result.getLocation());
        Assertions.assertEquals(coords, result.getCoords());
        Assertions.assertEquals(1.0, result.getStats().getCo());
        Assertions.assertEquals(3.0, result.getStats().getNo2());
        Assertions.assertEquals(6.0, result.getStats().getPm25());
        Assertions.assertEquals(8.0, result.getStats().getPm10());
        // Assert it was pushed to the Cache DB
        Mockito.verify(cacheService).postLocation(Mockito.any(Location.class));
    }


    @Test
    @DisplayName("Test location is not in cache, geolocation API fails")
    void testGetWeather_GeoLocationApiNotResponding() {
        // given
        String local = "London";
        Mockito.when(cacheService.getLocation(local)).thenReturn(null);


        String geocodingUrl = baseGeoCodingUrl + local + "&key=" + geoCodingApiKey + "&pretty=1&no_annotations=1&limit=1";


        Mockito.when(restTemplate.getForObject(geocodingUrl, GeocodingDTO.class))
                .thenReturn(null);

        // when
        Location result = weatherService.getWeather(local);

        // then
        Assertions.assertNull(result);
        Mockito.verify(cacheService,times(1)).getLocation(Mockito.any(String.class));
        Mockito.verify(cacheService,never()).postLocation((Mockito.any(Location.class)));

    }



    @Test
    @DisplayName("Test location is not in cache but second API responds")
    void testGetWeather_OpenWeatherApiNotResponding_MetoDoes() {
        // given
        String local = "London";

        GeocodingDTO geocodingDTO = new GeocodingDTO();

        Coords coords = new Coords(50, 50);

        Result resultDTO = new Result();
        resultDTO.bounds = new Bounds();
        resultDTO.bounds.northeast = new Northeast(50,50); 
        geocodingDTO.results = new ArrayList<Result>(); 
        geocodingDTO.results.add(resultDTO);

        OpenMetoStats openMetoStats = new OpenMetoStats();
        openMetoStats.hourly = new Hourly();
        double[] array = {25};
        openMetoStats.hourly.carbon_monoxide = array;
        openMetoStats.hourly.nitrogen_dioxide = array;
        openMetoStats.hourly.ozone = array;
        openMetoStats.hourly.pm10 = array;
        openMetoStats.hourly.pm2_5 = array;

        String geocodingUrl = baseGeoCodingUrl + local + "&key=" + geoCodingApiKey + "&pretty=1&no_annotations=1&limit=1";

        String openMetoUrl = baseOpenMetoUrl + "latitude=" + coords.getLat() + "&longitude=" + coords.getLgn() + "&&hourly=pm10,pm2_5,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone";

        
        Mockito.when(cacheService.getLocation(local)).thenReturn(null);
        
        Mockito.when(restTemplate.getForObject(geocodingUrl, GeocodingDTO.class))
                .thenReturn(geocodingDTO);


        Mockito.when(restTemplate.getForObject(openMetoUrl, OpenMetoStats.class))
        .thenReturn(openMetoStats);


        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(AirQualityDTO.class)))
                .thenThrow(new RestClientException("API not responding"));

        // when
        Location result = weatherService.getWeather(local);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(local, result.getLocation());
        Assertions.assertEquals(coords, result.getCoords());
        Assertions.assertEquals(25, result.getStats().getCo());
        Assertions.assertEquals(25, result.getStats().getNo2());
        Assertions.assertEquals(25, result.getStats().getPm25());
        Assertions.assertEquals(25, result.getStats().getPm10());
        // Assert it was pushed to the Cache DB
        Mockito.verify(cacheService).postLocation(Mockito.any(Location.class));

    }


    @Test
    void testGetWeather_OpenWeatherApi_and_Meto_NotResponding() {
        // given
        String local = "London";
        Mockito.when(cacheService.getLocation(local)).thenReturn(null);

        GeocodingDTO geocodingDTO = new GeocodingDTO();

        Result resultDTO = new Result();
        resultDTO.bounds = new Bounds();
        resultDTO.bounds.northeast = new Northeast(50,50); 
        geocodingDTO.results = new ArrayList<Result>(); 
        geocodingDTO.results.add(resultDTO);

        String geocodingUrl = baseGeoCodingUrl + local + "&key=" + geoCodingApiKey + "&pretty=1&no_annotations=1&limit=1";

        Mockito.when(restTemplate.getForObject(geocodingUrl, GeocodingDTO.class))
                .thenReturn(geocodingDTO);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(AirQualityDTO.class)))
                .thenThrow(new RestClientException("API not responding"));

        // when
        Location result = weatherService.getWeather(local);

        // then
        Assertions.assertNull(result);
        Mockito.verify(cacheService,times(1)).getLocation(Mockito.any(String.class));
        Mockito.verify(cacheService,never()).postLocation((Mockito.any(Location.class)));
        Mockito.verify(restTemplate,times(1)).getForObject(Mockito.any(String.class),Mockito.eq(GeocodingDTO.class));
        Mockito.verify(restTemplate,times(1)).getForObject(Mockito.any(String.class),Mockito.eq(AirQualityDTO.class));


    }

}
