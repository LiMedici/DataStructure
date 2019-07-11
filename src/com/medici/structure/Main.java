package com.medici.structure;

import com.medici.structure.linear.linked.LinkedList;
import com.medici.structure.linear.stack.LinkedQueue;
import com.medici.structure.linear.stack.LinkedStack;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*ArrayStack<Integer> stack = new ArrayStack<>(5);
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                stack.push(random.nextInt(Integer.MAX_VALUE));
            }else{
                stack.pop();
            }
            System.out.println(stack);
        }
        System.out.println(stack);*/

        /*LoopQueue<Integer> queue = new LoopQueue<>(5);
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                queue.enqueue(random.nextInt(Integer.MAX_VALUE));
            }else{
                queue.dequeue();
            }
            System.out.println(queue);
        }
        System.out.println(queue);*/

        /*LinkedList<Integer> list = new LinkedList<>();
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                list.addLast(random.nextInt(Integer.MAX_VALUE));
            }else{
                list.removeLast();
            }
            System.out.println(list);
        }
        System.out.println(list);*/

        /*LinkedStack<Integer> stack = new LinkedStack<>();
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                stack.push(random.nextInt(Integer.MAX_VALUE));
            }else{
                stack.pop();
            }
            System.out.println(stack);
        }
        System.out.println(stack);*/

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                queue.enqueue(random.nextInt(Integer.MAX_VALUE));
            }else{
                queue.dequeue();
            }
            System.out.println(queue);
        }
        System.out.println(queue);
    }
}
