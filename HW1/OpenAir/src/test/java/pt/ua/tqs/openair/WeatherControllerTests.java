package pt.ua.tqs.openair;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import pt.ua.tqs.openair.controller.WeatherController;
import pt.ua.tqs.openair.data.model.Coords;
import pt.ua.tqs.openair.data.model.Location;
import pt.ua.tqs.openair.data.model.Stats;
import pt.ua.tqs.openair.service.CacheService;
import pt.ua.tqs.openair.service.WeatherService;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTests {

    @MockBean
    private CacheService cacheService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private Logger logger;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetWeather_withValidLocalString() throws Exception {
        Location expectedLocation = new Location();
        expectedLocation.setLocation("gaia");
        expectedLocation.setCoords(new Coords(50, 50));
        expectedLocation.setStats(new Stats(13.3, 20.0, 20.0, 20.0));

        when(weatherService.getWeather("gaia")).thenReturn(expectedLocation);

        mockMvc.perform(get("/weather?local=gaia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("gaia"))
                .andExpect(jsonPath("$.coords.lat").value(50))
                .andExpect(jsonPath("$.coords.lgn").value(50))
                .andExpect(jsonPath("$.stats.co").value(13.3));
    }

    @Test
        public void testGetWeather_withInvalidLocalString() throws Exception {
            
            when(weatherService.getWeather(anyString())).thenReturn(null);
    
            mockMvc.perform(get("/weather?local=invalid-location"))
                    .andExpect(status().isNotFound());
        }

}
