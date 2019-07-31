package com.medici.structure.tree.heap;

import com.medici.structure.linear.queue.Queue;
import com.medici.structure.tree.heap.max.MaxHeap;

/**
 * 基于最大堆数据结构设计的优先队列
 * 性质：出队顺序和入队顺序无关, 和优先级相关
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
