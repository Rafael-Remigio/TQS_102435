package io.cucumber.skeleton;

import java.util.Stack;

public class Calculator {

    Stack<Integer> stack = new Stack<>();
    Integer result = 0;
    public int value(){
        return this.result;
    }
    public void push(Object here){


        if (here.getClass().equals(java.lang.String.class)){
            for (Integer i : stack){
                switch ((String) here) {
                    case "+":
                        this.result += i;
                        break;
                
                    case "-":
                        this.result = this.result - i;
                        break;
                    default:
                        break;
                }
            }
        }
        else {
            if (result == 0){
                this.result = (Integer) here;
                return;
            }
            stack.push((Integer) here);
        }
        
    }
}
