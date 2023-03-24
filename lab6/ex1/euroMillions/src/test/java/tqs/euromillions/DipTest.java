/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.euromillions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.*;
import tqs.euromillions.Dip;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
public class DipTest {

    private Dip sampleInstance;


    @BeforeEach
    public void setUp() {
        sampleInstance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        sampleInstance = null;
    }

    @DisplayName("format as string show all elements")
    @Test
    public void testFormat() {
        String result = sampleInstance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @DisplayName("new Dip rejects wrong size ou negatives")
    @Test
    public void testConstructorFromBadArrays() {

        // insufficient args
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11}, new int[]{} ) );

        //negative numbers
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11, 12, 13, -1}, new int[]{1, 2} ) );

        // this test will reveal that the code was not yet checking ranges


    }

    @DisplayName("new Dip rejects out of range elements")
    @Test
    public void testConstructorFromBadRanges() {
        // creating Dip with numbers or starts outside the expected range
        // expects an exception
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11, 12, 13, Dip.NUMBERS_RANGE_MAX * 2}, new int[]{1,2} ) );
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{11, 12, 13, 14, 15}, new int[]{ Dip.STARS_RANGE_MAX*2 ,1} ) );
        
        Dip dip = new Dip( new int[]{11, 12, 13, 14, 15}, new int[]{ 3 ,1} );
        BoundedSetOfNaturals numbers = new BoundedSetOfNaturals(Dip.NUMBERS_REQUIRED);
        numbers.add(new int[]{11, 12, 13, 14, 15});

        assertEquals(numbers, dip.getNumbersColl(),"getNumbersColl method does not return the correct numbers");

    }

    @DisplayName("Test hashCode method")
    @Test
    public void testHashCodeMethod() {

        Dip dip = new Dip( new int[]{11, 12, 13, 14, 15}, new int[]{ 3 ,1} );
        BoundedSetOfNaturals numbers = new BoundedSetOfNaturals(Dip.NUMBERS_REQUIRED);
        numbers.add(new int[]{11, 12, 13, 14, 15});

        BoundedSetOfNaturals stars = new BoundedSetOfNaturals(Dip.NUMBERS_REQUIRED);
        stars.add(new int[]{3,1});

        int hash = 3;
        hash = 29 * hash + Objects.hashCode(numbers);
        hash = 29 * hash + Objects.hashCode(stars);
        
        assertEquals(hash, dip.hashCode(),"HashCode method in dip does not equal the proper hash code");


    }


}
