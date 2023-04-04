package pt.ua.tqs.openair.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.tqs.openair.data.CacheRepository;
import pt.ua.tqs.openair.data.model.Info;
import pt.ua.tqs.openair.data.model.Location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    CacheRepository cacheRepository;

    public static final Info info = new Info(0, 0, 0);

    public Info getCacheInfo() {
        return info;
    }

    public Location getLocation(String locationAddress) {
        try {
            Optional<Location> location = cacheRepository.findById(locationAddress);

            incrementAccesses();

            if (location.isPresent()) {

                LOGGER.debug("Cache Hit: Location Object was Found");

                incrementHits();
                return location.get();
            } else {

                LOGGER.debug("Cache Miss: Location Object was NOT Found");

                incrementMisses();
                return null;
            }
            
        } catch (Exception e) {
            LOGGER.error("Cache Failure: failed accessing cache" + e.toString());

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

    public void postLocation(Location location) {

        try {
            
            cacheRepository.save(location);
            LOGGER.info("Cache Updated: New Location Object was added to Cache");

        } catch (Exception e) {

            LOGGER.error("Cache failed: Failed adding to Cache");



        }

    }

}
