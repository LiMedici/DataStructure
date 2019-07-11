package com.medici.structure.linear.linked;

/**
 * 动态数据结构
 * 优点：动态数据结构，不需要扩容缩容
 * 缺点：内存是不连续的，不方便查询
 */
public class LinkedList<E> {
    // 增，删，改，查
    // 只对链表头进行操作 O(1)
    // 链表实现栈，链表头作为栈的顶层实现
    // 链表优化，添加tail作为队列的队尾，head作为队列的队首

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        add(0, e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    // 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++){
            prev = prev.next;
        }

        prev.next = new Node(e,prev.next);
        size++;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return removeAt(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return removeAt(size - 1);
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E removeAt(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++){
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    // 从链表中删除元素e
    public void removeElement(E e){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    // 修改链表的第index(0-based)个位置的元素为e
    // 在链表中不是一个常用的操作，练习用：）
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++){
            cur = cur.next;
        }

        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;

        while(cur != null){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    // 获得链表的第index(0-based)个位置的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E get(int index){

        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null){
            result.append(cur + "->");
            cur = cur.next;
        }
        result.append("NULL");
        return result.toString();
    }

    private class Node{
        public E e;
        public Node next;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this(e,null);
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
