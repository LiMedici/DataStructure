package com.medici.structure.linear.queue;

import com.medici.structure.linear.queue.Queue;

public class LinkedQueue<E> implements Queue<E> {

    private Node head;
    private Node tail;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }
        else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node node = head;
        head = head.next;
        node.next = null;

        if(head == null) tail = null;
        size--;

        return node.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getCapacity() {
        throw new IllegalStateException("LinkedStack getCapacity method not defined");
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public E peek() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");

        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            result.append(cur + "->");
            cur = cur.next;
        }
        result.append("NULL tail");
        return result.toString();
    }

    private class Node{
        public E e;
        public Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e){
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
