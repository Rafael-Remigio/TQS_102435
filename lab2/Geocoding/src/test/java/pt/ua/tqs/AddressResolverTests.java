package pt.ua.tqs;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressResolverTests 
{


    @Mock
    SimpleHttpResolver httpResolver;
    
    @InjectMocks
    AddressResolver addressResolver;

    @Test
    public void testFindAddressForLocation() throws Exception
    {
        String longitude = "-70.0";
        String latitude = "70.0";
        String location = "http://www.mapquestapi.com/geocoding/v1/reverse?key=KEY&location="+latitude+","+ longitude;
        String expected = "{\"street\": \"12714 Ashley Melisse Blvd\", \"adminArea6\": \"\",\"adminArea6Type\": \"Neighborhood\",\"adminArea5\": \"Jacksonville\",\"adminArea5Type\": \"City\",\"adminArea4\": \"Duval\",\"adminArea4Type\": \"County\",\"adminArea3\": \"FL\",\"adminArea3Type\": \"State\",\"adminArea1\": \"US\",\"adminArea1Type\": \"Country\",\"postalCode\": \"32225\",\"geocodeQualityCode\": \"L1AAA\",\"geocodeQuality\": \"ADDRESS\",\"dragPoint\": false,\"sideOfStreet\": \"R\",\"linkId\": \"0\",\"unknownInput\": \"\",\"type\": \"s\"";

        when(httpResolver.doHttpGet(location)).thenReturn(expected);

        String badStringLatitude = "70.0";
        String badStrindLongitude = "70.0";
        String BadStringlocation = "http://www.mapquestapi.com/geocoding/v1/reverse?key=KEY&location="+badStringLatitude+","+ badStrindLongitude;
        String BadStringexpected = "{\"street\": \"12714 Ashley Melisse Blvd\", \"adminArea6\": \"\"\"adminArea6Type\" \"Neighborhood\",\"adminArea5\" \"Jacksonville\",\"adminArea5Type\": \"City\",\"adminArea4\": \"Duval\",\"adminArea4Type\": \"County\",\"adminArea3\": \"FL\",\"adminArea3Type\": \"State\",\"adminArea1\": \"US\",\"adminArea1Type\": \"Country\",\"postalCode\": \"32225\",\"geocodeQualityCode\": \"L1AAA\",\"geocodeQuality\": \"ADDRESS\",\"dragPoint\": false,\"sideOfStreet\": \"R\",\"linkId\": \"0\",\"unknownInput\": \"\",\"type\": \"s\"";
        when(httpResolver.doHttpGet(BadStringlocation)).thenReturn(BadStringexpected);


        
        assertEquals(new Address("Ashley Melisse Blvd", "FL", "Jacksonville", "32225", "12714"),addressResolver.finAddressForLocation(70, -70),"Address object does not equal what was expected");


        assertThrows(Exception.class,  () -> addressResolver.finAddressForLocation(70, 70), "Should Returns Excection because given json String is badly built");


        assertThrows(IllegalArgumentException.class, () -> addressResolver.finAddressForLocation(-93, 190));

    }
}
