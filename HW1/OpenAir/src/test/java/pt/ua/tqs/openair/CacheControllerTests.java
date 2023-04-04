package pt.ua.tqs.openair;

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

import pt.ua.tqs.openair.controller.CacheController;
import pt.ua.tqs.openair.data.model.Info;
import pt.ua.tqs.openair.service.CacheService;
import pt.ua.tqs.openair.service.WeatherService;

@WebMvcTest(CacheController.class)
public class CacheControllerTests {


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
    public void testGetCacheInfo() throws Exception {
        
        long hits = 10;
        long misses = 20;
        long acesses = 30;
        Info expectedInfo = new Info(hits,misses,acesses);

        when(cacheService.getCacheInfo()).thenReturn(expectedInfo);

        mockMvc.perform(get("/cacheInfo"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.hits").value(10))
               .andExpect(jsonPath("$.misses").value(20))
               .andExpect(jsonPath("$.acesses").value(30));
    }

    

}
