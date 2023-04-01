package pt.ua.tqs.OpenAir.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import pt.ua.tqs.OpenAir.Data.CacheRepository;
import pt.ua.tqs.OpenAir.Data.Model.Location;

public class CacheService {


    @Autowired
    CacheRepository cacheRepository;
    
    public String getCacheInfo(){
        return " ";
    }

    public Optional<Location> getLocation(String locationAddress){
    
        return cacheRepository.findById(locationAddress);
    }

    public void postLocation(Location location){
        cacheRepository.save(location);
    }

}
