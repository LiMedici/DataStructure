package com.medici.structure.linear.queue;

/**
 * 循环队列，环形队列 FIFO First In First Out 树的广度优先遍历(层序遍历)
 * 增删的均摊复杂度为O(1)
 */
public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int size;
    private int front;
    private int tail;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
    }

    @Override
    public void enqueue(E e) {
        // 入队先检查是否容量已满
        if((tail + 1) % data.length == front){
            // 调整大小
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E element = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }

        return element;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++){
            newData[i] = data[(i + front) % data.length];
        }
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public E peek() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        stringBuilder.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length){
            stringBuilder.append(data[i]);
            if((i + 1) % data.length != tail){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
