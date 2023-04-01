package pt.ua.tqs.OpenAir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.OpenAir.Service.CacheService;

@RestController
public class CacheController {
    
    @Autowired
    CacheService cacheService;


    @GetMapping("/cacheInfo")
    public String getCacheInfo(
    ) {
        return cacheService.getCacheInfo();
    }

}
