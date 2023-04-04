package pt.ua.tqs.openair;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.tqs.openair.data.CacheRepository;
import pt.ua.tqs.openair.data.model.Info;
import pt.ua.tqs.openair.data.model.Location;
import pt.ua.tqs.openair.service.CacheService;

@ExtendWith(MockitoExtension.class)
public class CacheServiceTests {
    

    @Mock
    CacheRepository cacheRepository;

    @InjectMocks
    CacheService cacheService;



    @BeforeEach
    void init() {
        // Clean Up
        CacheService.info.setAcesses(0);
        CacheService.info.setHits(0);
        CacheService.info.setMisses(0);
    }

    @Test
    @DisplayName("Test getCacheInfo method")
    void testGetCacheInfo() {
        Info info = cacheService.getCacheInfo();
        assertEquals(0, info.getHits());
        assertEquals(0, info.getMisses());
        assertEquals(0, info.getAcesses());
    }



    @Test
    @DisplayName("Test getLocation method when cache hit occurs")
    void testGetLocationCacheHit() {
        String locationAddress = "New York";
        Location expectedLocation = new Location(locationAddress, null, null);
        Optional<Location> optionalLocation = Optional.of(expectedLocation);

        when(cacheRepository.findById(locationAddress)).thenReturn(optionalLocation);

        Info info = cacheService.getCacheInfo();
        assertEquals(0, info.getHits());
        assertEquals(0, info.getMisses());
        assertEquals(0, info.getAcesses());

        Location actualLocation = cacheService.getLocation(locationAddress);

        info = cacheService.getCacheInfo();
    
        assertEquals(expectedLocation, actualLocation);
        assertEquals(1, info.getHits());
        assertEquals(1, info.getAcesses());
        assertEquals(0, info.getMisses());

        verify(cacheRepository, times(1)).findById(locationAddress);
    }


    @Test
    @DisplayName("Test getLocation method when cache miss occurs")
    void testGetLocationCacheMiss() {
        String locationAddress = "London";
        Optional<Location> optionalLocation = Optional.empty();

        when(cacheRepository.findById(locationAddress)).thenReturn(optionalLocation);

        Location actualLocation = cacheService.getLocation(locationAddress);

        Info info = cacheService.getCacheInfo();

        assertEquals(null, actualLocation);
        assertEquals(0, info.getHits());
        assertEquals(1, info.getAcesses());
        assertEquals(1, info.getMisses());

        verify(cacheRepository, times(1)).findById(locationAddress);
    }


    @Test
    @DisplayName("Test postLocation method")
    void testPostLocation() {
        String locationAddress = "Paris";
        Location expectedLocation = new Location(locationAddress, null, null);

        when(cacheRepository.save(expectedLocation)).thenReturn(expectedLocation);

        cacheService.postLocation(expectedLocation);

        verify(cacheRepository, times(1)).save(expectedLocation);
    }

}
