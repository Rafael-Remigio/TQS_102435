/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;



    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(20);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        assertThrows(IllegalArgumentException.class,() -> setB.add(11), "adding an element should return an exception");
        assertEquals(6, setB.size(), "add: elements count not as expected.");

        setD.add(20);
        assertTrue(setD.contains(20), "add: added element not found in set.");
        assertEquals(1, setA.size(), "add: elements count not as expected.");

        assertThrows(IllegalArgumentException.class,() -> setD.add(-10), "adding an element should return an exception");
        assertThrows(IllegalArgumentException.class,() -> setD.add(-10), "adding an element should return an exception");
        assertThrows(IllegalArgumentException.class,() -> setD.add(20), "adding an element should return an exception");
        assertEquals(1, setA.size(), "add: elements count not as expected.");

    }

    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(elems));


        int[] elems2 = new int[]{10, 20, 10};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(elems2));
    }

    @DisplayName("Intersection of bounded set arrays")
    @Test
    public void testInterction(){
        assertTrue(setB.intersects(setC), "interction: set interction should return true but returns false");

        assertFalse(setB.intersects(setD), "interction: set interction should return false in the intersection between an empty and a non empty set");

        setA.add(25);
        assertFalse(setB.intersects(setA), "interction: set interction should return false but return true");


    }


}
