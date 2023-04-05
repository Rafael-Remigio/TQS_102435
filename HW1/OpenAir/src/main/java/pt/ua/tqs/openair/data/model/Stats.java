package pt.ua.tqs.openair.data.model;

public class Stats {
    
    private double co;
    private double no2;
    private double pm25;
    private double pm10;

    

    public Stats(double co, double no2, double pm25, double pm10) {
        this.co = co;
        this.no2 = no2;
        this.pm25 = pm25;
        this.pm10 = pm10;
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
    public double getPm25() {
        return pm25;
    }
    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }
    public double getPm10() {
        return pm10;
    }
    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(co);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(no2);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pm25);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pm10);
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
        Stats other = (Stats) obj;
        if (Double.doubleToLongBits(co) != Double.doubleToLongBits(other.co))
            return false;
        if (Double.doubleToLongBits(no2) != Double.doubleToLongBits(other.no2))
            return false;
        if (Double.doubleToLongBits(pm25) != Double.doubleToLongBits(other.pm25))
            return false;
        if (Double.doubleToLongBits(pm10) != Double.doubleToLongBits(other.pm10))
            return false;
        return true;
    }

    
    
}
