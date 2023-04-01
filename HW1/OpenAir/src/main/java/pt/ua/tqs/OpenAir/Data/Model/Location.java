package pt.ua.tqs.OpenAir.Data.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 60L)
public class Location {


    private double lat;
    private double lgn;

    @Id
    private String location;
    
    private double co;
    private double no2;
    private double o3;
    private double pm2_5;
    private double pm10;

    public Location(double lat, double lgn, String location, double co, double no2, double o3, double pm2_5,
            double pm10) {
        this.lat = lat;
        this.lgn = lgn;
        this.location = location;
        this.co = co;
        this.no2 = no2;
        this.o3 = o3;
        this.pm2_5 = pm2_5;
        this.pm10 = pm10;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLgn() {
        return lgn;
    }

    public void setLgn(double lgn) {
        this.lgn = lgn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public double getO3() {
        return o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public double getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(double pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    @Override
    public String toString() {
        return "Location [lat=" + lat + ", lgn=" + lgn + ", location=" + location + ", co=" + co + ", no2=" + no2
                + ", o3=" + o3 + ", pm2_5=" + pm2_5 + ", pm10=" + pm10 + "]";
    }
    

}