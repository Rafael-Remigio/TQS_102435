package pt.ua.tqs;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {
    SimpleHttpResolver httpClient = new SimpleHttpResolver();

    @BeforeEach
    public void init(){
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws Exception {

        
        
        AddressResolver resolver = new AddressResolver(httpClient);

        Address result = resolver.finAddressForLocation(70, 70);
 
        assertEquals( new Address("Ashley Melisse Blvd", "FL", "Jacksonville", "32225", "12714"), result);
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws Exception {
        
        
        AddressResolver resolver = new AddressResolver(httpClient);

        assertThrows(IllegalArgumentException.class, () -> resolver.finAddressForLocation(-93, 190));

    }

}

