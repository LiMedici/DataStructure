package com.medici.structure.map;

/**
 * 映射相关方法定义
 * @param <K> 键
 * @param <V> 值
 */
public interface Map<K,V> {

    void put(K key,V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    boolean isEmpty();
    int getSize();

}
