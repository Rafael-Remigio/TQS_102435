package pt.ua.tqs.openair.data.model;

public class Coords {
    

    private double lat;
    private double lgn;

    

    public Coords(double lat, double lgn) {
        this.lat = lat;
        this.lgn = lgn;
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


    

}
