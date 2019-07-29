package com.medici.structure.map;

import com.medici.structure.set.FileOperation;

import java.util.ArrayList;

/**
 * 基于二分搜索树数据结构实现的映射(字典)
 * 时间复杂度O(logn)
 */
public class TreeMap<K extends Comparable<K>,V> implements Map<K,V>{

    private Node root;
    private int size;

    @Override
    // 向二分搜索树中添加新的元素(key, value)
    public void put(K key, V value) {
        root = put(root,key,value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node put(Node node,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }

        if(node.key.compareTo(key) < 0){
            node.left = put(node.left,key,value);
        }else if(node.key.compareTo(key) > 0){
            node.right = put(node.right,key,value);
        }else{
            node.value = value;
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(K key){
        return getNode(root,key);
    }

    /**
     * 在以node为根的二分搜索树上寻找key的节点
     */
    private Node getNode(Node node, K key){
        if(node == null) return null;

        if(node.key.compareTo(key) < 0){
            return getNode(node.left,key);
        }else if(node.key.compareTo(key) > 0){
            return getNode(node.right,key);
        }

        return node;
    }

    @Override
    // 从二分搜索树中删除键为key的节点
    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除以node为根的二分搜索树键值为key的节点
     * 并返回删除的节点V
     */
    private Node remove(Node node,K key){
        if(node == null) return null;

        if(node.key.compareTo(key) < 0){
            node.left = remove(node.left,key);
            return node;
        }else if(node.key.compareTo(key) > 0){
            node.right = remove(node.right,key);
            return node;
        }else{
            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点小的最大节点, 即待删除节点左子树的最大节点
            // 用这个节点顶替待删除节点的位置
            Node precursor = maximum(node.right);
            precursor.left = removeMax(node.right);
            precursor.right = node.right;
            node.left = node.right = null;
            return precursor;
        }
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node){
        if(node.right == null) return node;
        return maximum(node.right);
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){
        if(node.right == null){
            // 找到该节点
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public boolean contains(K key) {
        Node node = getNode(key);
        return node != null;
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
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this(key,value,null,null);
        }

        public Node(K key, V value, Node left,Node right){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }
}
