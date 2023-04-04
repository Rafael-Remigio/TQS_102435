package pt.ua.tqs.openair.data.dto.openweather;

public class Components{
    public double co;
    public double no;
    public double no2;
    public double o3;
    public double so2;
    public double pm2_5;
    public double pm10;
    public double nh3;
    
    public Components(double co, double no, double no2, double o3, double so2, double pm2_5, double pm10, double nh3) {
        this.co = co;
        this.no = no;
        this.no2 = no2;
        this.o3 = o3;
        this.so2 = so2;
        this.pm2_5 = pm2_5;
        this.pm10 = pm10;
        this.nh3 = nh3;
    }


    
}
