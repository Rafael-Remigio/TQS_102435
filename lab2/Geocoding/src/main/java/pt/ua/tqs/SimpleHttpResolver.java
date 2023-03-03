package pt.ua.tqs;

public class SimpleHttpResolver extends TqsBasicHttpClient{

    public String doHttpGet(String location) {
        return "response";
    }

}
