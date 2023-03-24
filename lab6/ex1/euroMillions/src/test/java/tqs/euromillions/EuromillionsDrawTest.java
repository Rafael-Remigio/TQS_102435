package tqs.euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.euromillions.CuponEuromillions;
import tqs.euromillions.Dip;
import tqs.euromillions.EuromillionsDraw;

public class EuromillionsDrawTest {

    private CuponEuromillions sampleCoupon;

    @BeforeEach
    public void setUp()  {
        sampleCoupon = new CuponEuromillions();
        sampleCoupon.appendDip(Dip.generateRandomDip());
        sampleCoupon.appendDip(Dip.generateRandomDip());
        sampleCoupon.appendDip(new Dip(new int[]{1, 2, 3, 48, 49}, new int[]{1, 9}));
    }


    @DisplayName("reports correct matches in a coupon")
    @Test
    public void testCompareBetWithDrawToGetResults() {
        Dip winningDip, matchesFound;

        // test for full match, using the 3rd dip in the coupon as the Draw results
        winningDip = sampleCoupon.getDipByIndex(2);
        EuromillionsDraw testDraw = new EuromillionsDraw(winningDip);
        matchesFound = testDraw.findMatchesFor(sampleCoupon).get(2);

        assertEquals(winningDip, matchesFound, "expected the bet and the matches found to be equal");

        // test for no matches at all
        testDraw = new EuromillionsDraw(new Dip(new int[]{9, 10, 11, 12, 13}, new int[]{2, 3}));
        matchesFound = testDraw.findMatchesFor(sampleCoupon).get(2);
        // compare empty with the matches found
        assertEquals( new Dip(), matchesFound);
    }


    @DisplayName("tests if the countDips method of is correct")
    @Test
    public void testSize(){
        assertEquals(3, sampleCoupon.countDips(),"size of dips should return 3 but return something else");
        sampleCoupon.appendDip(Dip.generateRandomDip());
        assertEquals(4, sampleCoupon.countDips(),"size of dips should return 4 after an insert but return something else");
    }

    @DisplayName("formats the dip collection into a String")
    @Test
    public void testFormat(){
        Dip dip1 = sampleCoupon.getDipByIndex(0);
        Dip dip2 = sampleCoupon.getDipByIndex(1);
        Dip dip3 = sampleCoupon.getDipByIndex(2);

        String correctFormat = "Dip #1:" + dip1.format() + "\n" + "Dip #2:" + dip2.format() + "\n" + "Dip #3:" + dip3.format() + "\n";
        
        assertEquals(correctFormat, sampleCoupon.format(),"Coupon format does not equal the expected format");

    }

    @DisplayName("generates random draw and returns the correct results")
    @Test
    public void testDrawingResults(){
        Dip dip = Dip.generateRandomDip();
        EuromillionsDraw draw = new EuromillionsDraw(dip);

        assertEquals(dip, draw.getDrawResults(), "drawresults method is not returning the proper results");
        
        

    }

}
