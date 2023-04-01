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

    
    
}
