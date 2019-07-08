package com.medici.structure.linear.queue;

public interface Queue<E> {

    void enqueue(E e);
    E dequeue();
    boolean isEmpty();
    int getCapacity();
    int getSize();
    E peek();

}
