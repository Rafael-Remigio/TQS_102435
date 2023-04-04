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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lgn);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Coords other = (Coords) obj;
        if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
            return false;
        if (Double.doubleToLongBits(lgn) != Double.doubleToLongBits(other.lgn))
            return false;
        return true;
    }

    


    

}
