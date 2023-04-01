package pt.ua.tqs.openair.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.tqs.openair.data.CacheRepository;
import pt.ua.tqs.openair.data.model.Info;
import pt.ua.tqs.openair.data.model.Location;


@Service
public class CacheService {



    @Autowired
    CacheRepository cacheRepository;

    static final Info info = new Info(0, 0, 0);
    
    public Info getCacheInfo(){
        return info;
    }

    public Location getLocation(String locationAddress){

            Optional<Location> location = cacheRepository.findById(locationAddress);

            incrementAccesses();

            if (location.isPresent()){
                incrementHits();
                return location.get();
            }
            else {
                incrementMisses();
                return null;
            }
    }

    private void incrementMisses() {
        info.setMisses(info.getMisses() + 1);
    }

    private void incrementHits() {
        info.setHits(info.getHits() + 1);
    }

    private void incrementAccesses() {
        info.setAcesses(info.getAcesses() + 1);
    }

    public void postLocation(Location location){
        cacheRepository.save(location);
    }


}
