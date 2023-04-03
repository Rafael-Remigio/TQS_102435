package pt.ua.tqs.openair.data.dto.geocoding;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Components{
    @JsonProperty("ISO_3166-1_alpha-2") 
    public String iSO_31662;
    @JsonProperty("ISO_3166-1_alpha-3") 
    public String iSO_31663;
    @JsonProperty("ISO_3166-2") 
    public ArrayList<String> listiSO_31662;
    public String _category;
    public String _type;
    public String city;
    public String city_district;
    public String continent;
    public String country;
    public String country_code;
    public String county;
    public String municipality;
    public String postcode;
    public String region;
    public String road;
    public String road_type;
    public String state;
    public String state_code;
    public String state_district;
    public String suburb;
}
