package io.cucumber.skeleton;

public class Belly {
    int cukes;
    int time;
    public void eat(int cukes) {
        this.cukes = cukes;
    }
    public void waitTime(int hours){
        System.out.println("Digesting "+ cukes+" cupcakes");
        this.time = hours;
    }
    public void growl(){
        double ratio = cukes/time;
        System.out.print("G");
        for (int i = 0;i<ratio;i++ ){
            System.out.print("r");
        }

        System.out.println(" ");
    }
}
