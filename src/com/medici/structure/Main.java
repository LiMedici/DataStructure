package com.medici.structure;

import com.medici.structure.linear.stack.ArrayStack;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                stack.push(random.nextInt(Integer.MAX_VALUE));
            }else{
                stack.pop();
            }
            System.out.println(stack);
        }
        System.out.println(stack);
    }
}
