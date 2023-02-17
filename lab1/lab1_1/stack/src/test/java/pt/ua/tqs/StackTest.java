package pt.ua.tqs;
import static org.junit.jupiter.api.Assertions.*;


import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;
/**
 * Unit test for simple Stack.
 */
public class StackTest
 {

    protected Stack stack;

    @BeforeEach
    public void createNewStack(){
        stack = new Stack();
    }

    @DisplayName("Tests if the stack is empty on creation")
    @Test
    public void testNewStackEmpty(){
        assertTrue(stack.isEmpty(),"Method isEmpty should return true but returns false");
        assertEquals(0,stack.size(), "Method size should return 0 but return something else");
        
    }

    @DisplayName("Test pushes to empty stack")
    @Test
    public void testPushesToEmptyStack(){
        for (int i=0; i<8;i++){
            stack.push(i);
        }        
        assertEquals(8, stack.size(), "Method size should return 8, but returns something else");
        assertFalse(stack.isEmpty(),"Method isEmpty should return false but returns true");

    }

    @DisplayName("Test pushing an item and then popping that same item")
    @Test
    public void testPushThenPop(){
        String pushed = "item";
        stack.push(pushed);
        Object item = stack.pop();

        assertEquals(pushed,item, "The pushed item is different to the poped item");

        assertTrue(stack.isEmpty(),"Method isEmpty should return true but returns false");
        assertEquals(0,stack.size(), "Method size should return 0 but return something else");
    }
    
    @DisplayName("Test pushing an item and then peeking that same item")
    @Test
    public void testPushThenPeek(){
        String pushed = "item";
        stack.push(pushed);
        Object item = stack.peek();

        assertEquals(pushed,item, "The pushed item is different to the peeked item");
        assertFalse(stack.isEmpty(),"Method isEmpty should return false but returns true");
        assertEquals(1,stack.size(), "Method size should return 0 but return something else");
   
    }

    @DisplayName("Test multiple pops on a stack until it becomes empty")
    @Test
    public void testMultiplePops(){
        stack.push("here1");
        stack.push("here2");
        stack.push("here3");
        stack.push("here4");

        assertEquals(4,stack.size(), "The stack should have size 4 but returns something else");

        stack.pop();
        Object here = stack.pop();
        
        assertEquals("here3", here,"Poped item should be equal to here3 but it isn't");
        assertFalse(stack.isEmpty(),"Method isEmpty should return false but returns true");
        assertEquals(2,stack.size(), "The stack method size shout return 4 but returns something else");


        stack.pop();
        here = stack.pop();

        assertEquals("here1",here, "The pushed item is different to the poped item");

        assertTrue(stack.isEmpty(),"Method isEmpty should return true but returns false");
        assertEquals(0,stack.size(), "Method size should return 0 but return something else");

    }

    @DisplayName("Testing Exception trying to remove from an empty list")
    @Test
    public void testPopOnEmptyStackThrows() {
        assertTrue(stack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }

    @DisplayName("Testing Exception trying to peek from an empty list")
    @Test
    public void testPeekIntoEmptyStackThrows() {
        assertTrue(stack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }
    

}
