package pt.ua.tqs.openair.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 60L)
public class Location {

    @Id
    private String address;

    private Coords coords;

    private Stats stats;

    public Location(String address, Coords coords, Stats stats) {
        this.address = address;
        this.coords = coords;
        this.stats = stats;
    }

    public String getLocation() {
        return address;
    }

    public void setLocation(String address) {
        this.address = address;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

}