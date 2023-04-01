package pt.ua.tqs.openair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.openair.data.model.Info;
import pt.ua.tqs.openair.service.CacheService;


@RestController
public class CacheController {
    
    @Autowired
    CacheService cacheService;



    @GetMapping("/cacheInfo")
    public Info getCacheInfo(
    ) {
        return cacheService.getCacheInfo();
    }




}
