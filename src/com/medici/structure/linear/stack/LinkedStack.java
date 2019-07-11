package com.medici.structure.linear.stack;

import com.medici.structure.linear.linked.LinkedList;

public class LinkedStack<E> implements Stack<E>{

    private LinkedList<E> list;

    public LinkedStack() {
        list = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.get(0);
    }

    @Override
    public int getCapacity() {
        throw new IllegalStateException("LinkedStack getCapacity method not defined");
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Stack: top ");
        result.append(list);
        return result.toString();
    }
}
