package pt.ua.tqs;

import java.util.HashMap;
import java.util.Map;


public class AddressResolver {
    SimpleHttpResolver simpreResolver;

    public AddressResolver(SimpleHttpResolver simpreResolver) {
        this.simpreResolver = simpreResolver;
    }

    public Address finAddressForLocation(double latitude,double longitude) throws Exception {

        if ((-90 > latitude || latitude > 90 || -180 > longitude || longitude > 180)){
            throw new IllegalArgumentException();
        }


        String location = "http://www.mapquestapi.com/geocoding/v1/reverse?key=KEY&location="+latitude+","+ longitude;
        String response = simpreResolver.doHttpGet(location);
        HashMap<String,String> jsonMap;
        try {
            jsonMap = jsonStringtoHashMap(response);

        } catch (Exception e) {
            throw new Exception("Bad String response");
        }

        try {
            return new Address(jsonMap.get("street").split(" ",2)[1], jsonMap.get("adminArea3"), jsonMap.get("adminArea5"), jsonMap.get("postalCode"), jsonMap.get("street").split(" ")[0]);

        } catch (Exception e) {
            throw new Exception("Bad Json HashMap");
        }


    }


    private HashMap<String,String> jsonStringtoHashMap(String jsonString){
        jsonString = jsonString.substring(1, jsonString.length()-1);           //remove curly brackets
        String[] keyValuePairs = jsonString.split(",");
        HashMap<String,String> map = new HashMap<>();               

        for(String pair : keyValuePairs)                        //iterate over the pairs
        {
            String[] entry = pair.split(":");                   //split the pairs to get key and value 
            String value = entry[1].trim();
            String key = entry[0].trim();
            if (value.contains("\"")){
                value = value.substring(1, value.length()-1);
            }
            map.put(key.substring(1, key.length()-1) , value);          //add them to the hashmap and trim whitespaces
        }


        return map;
    }
}
