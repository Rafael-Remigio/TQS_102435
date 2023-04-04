package pt.ua.tqs.openair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.ua.tqs.openair.data.model.Info;
import pt.ua.tqs.openair.service.CacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    CacheService cacheService;

    @GetMapping("/cacheInfo")
    public Info getCacheInfo() {
        try{
           
        LOGGER.debug("Received a cache information request");
        Info info = cacheService.getCacheInfo();

        if (info == null){
            throw new ServiceUnavailableException(null);
        }

        return info;
         
        }
        catch (ServiceUnavailableException exc) {
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "Cache Information not available", exc);
        }
    }



}
