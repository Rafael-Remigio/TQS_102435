package pt.ua.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack 
{
    LinkedList<Object> list = new LinkedList<Object>();



    public void push(Object obj){
        list.add(0, obj);
    }

    public Object pop() throws NoSuchElementException{
        if (list.size() != 0) {
            return list.remove(0);            
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public int size(){
        return list.size();   
    }

    public Object peek(){
        if (list.size() != 0) {
            return list.getFirst();            
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

}
