package com.medici.structure.map;

/**
 * 基于链表数据结构实现的映射，(字典) 时间复杂度O(n)
 */
public class LinkedListMap<K,V> implements Map<K,V>{

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            // 添加到头结点
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else{
            node.value = value;
        }
    }

    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key)){
                return cur;
            }

            cur = cur.next;
        }

        return null;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(){
            this(null,null);
        }

        public Node(K key, V value) {
            this(key,value,null);
        }

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }
}
