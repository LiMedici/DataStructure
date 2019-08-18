package com.medici.structure.map;

import com.medici.structure.tree.avl.AVLTree;

public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVLTree<K, V> avl;

    public AVLMap(){
        avl = new AVLTree<>();
    }

    @Override
    public int getSize(){
        return avl.getSize();
    }

    @Override
    public boolean isEmpty(){
        return avl.isEmpty();
    }

    @Override
    public void put(K key, V value){
        avl.add(key, value);
    }

    @Override
    public boolean contains(K key){
        return avl.contains(key);
    }

    @Override
    public V get(K key){
        return avl.get(key);
    }

    @Override
    public V remove(K key){
        return avl.remove(key);
    }
}
