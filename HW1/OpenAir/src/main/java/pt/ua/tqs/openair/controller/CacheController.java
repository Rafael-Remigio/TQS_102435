package pt.ua.tqs.openair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        LOGGER.debug("Received a cache information request");
        Info info = cacheService.getCacheInfo();
        return info;
    }

}
