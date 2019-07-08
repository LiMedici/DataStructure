package com.medici.structure.linear.queue;

import com.medici.structure.linear.array.Array;

/**
 * 数组队列 FIFO First In First Out 树的广度优先遍历(层序遍历)
 * 缺点:删除队列元素的复杂度O(n)
 */
public class ArrayQueue<E> implements Queue<E>{

    private Array<E> array;

    public ArrayQueue(){
        this(10);
    }

    public ArrayQueue(int capacity){
        array = new Array<E>(capacity);
    }

    @Override
    public void enqueue(E e) {
        // 队尾添加一个
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue: front [");
        for (int i = 0; i < array.getSize(); i++){
            stringBuilder.append(array.get(i));
            if(i != array.getSize() - 1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
