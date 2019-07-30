package com.medici.structure.heap;

import com.medici.structure.heap.max.MaxHeap;
import com.medici.structure.linear.queue.Queue;

/**
 * 基于最大堆数据结构设计的优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> heap;

    public PriorityQueue() {
        heap = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.extractMax();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int getCapacity() {
        throw new IllegalStateException("PriorityQueue getCapacity method not defined");
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public E peek() {
        return heap.findMax();
    }
}
