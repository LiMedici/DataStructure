package com.medici.structure.linear.recursion;

import com.medici.structure.linear.recursion.RecursionLinkedList;
import com.medici.structure.linear.stack.Stack;

public class RecursionLinkedStack<E> implements Stack<E> {

    private RecursionLinkedList<E> list;

    public RecursionLinkedStack(){
        list = new RecursionLinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public void push(E e){
        list.addFirst(e);
    }

    @Override
    public E pop(){
        return list.removeFirst();
    }

    @Override
    public E peek(){
        return list.getFirst();
    }

    @Override
    public int getCapacity() {
        throw new IllegalStateException("LinkedStack getCapacity method not defined");
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

}
