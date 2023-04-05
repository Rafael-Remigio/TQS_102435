package pt.ua.tqs.openair.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 5L)
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

    public Location(){
        
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((coords == null) ? 0 : coords.hashCode());
        result = prime * result + ((stats == null) ? 0 : stats.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (coords == null) {
            if (other.coords != null)
                return false;
        } else if (!coords.equals(other.coords))
            return false;
        if (stats == null) {
            if (other.stats != null)
                return false;
        } else if (!stats.equals(other.stats))
            return false;
        return true;
    }


    
}