package com.medici.structure.hash;

import com.medici.structure.map.Map;

import java.util.TreeMap;

/**
 * 哈希表数据结构，均摊复杂度结构为O(1)
 * 1.hash函数的设计。
 * 2.解决hash冲突：
 *      （1）链地址法。hash数组每个坐标位置放置链表或者红黑树。
 *      （2）开放地址法。
 *          a. 线性探测法。
 *          b. 平方探测法。
 *          c. 二次hash法。
 *      （3）再hash法。
 * 3.hash函数设计，设计返回为int类型的hash值，hash表容量为M（素数），hash函数的一种公式
 *       hash(code) = （c * B^3 + o * B^2 + d * B^1 + e * B^0）% M
 *       hash(code) =  ((((c * B) + o) * B + d) * B + e) % M
 *       hash(code) =  ((((c % M) * B + o) % M * B + d) % M * B + e) % M
 */
public class HashTable<K extends Comparable<K>,V> implements Map<K,V> {

    private int m;
    private int size;
    private TreeMap[] table;

    private int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private int capacityIndex;
    // 冲突的上行，下行表示
    private static final int upperTol = 10;
    private static final int lowerTol = 2;

    public HashTable(){
        this.size = 0;
        this.capacityIndex = 0;
        this.m = capacity[capacityIndex];
        this.table = new TreeMap[m];

        for (int i = 0; i < m; i++){
            table[i] = new TreeMap();
        }
    }


    @Override
    public void put(K key, V value) {
        TreeMap<K,V> map = table[hash(key)];
        if(!map.containsKey(key)){
            map.put(key,value);
            size++;

            if(size >= upperTol * m && capacityIndex + 1 < capacity.length){
                // 扩容
                this.capacityIndex++;
                resize(capacityIndex);
            }
        }else{
            map.put(key,value);
        }
    }

    @Override
    public V remove(K key) {
        TreeMap<K,V> map = table[hash(key)];
        if(map.containsKey(key)){
            V value = map.remove(key);
            size--;

            if(size < lowerTol * m && capacityIndex > 0){
                // 缩容
                this.capacityIndex--;
                resize(capacityIndex);
            }

            return value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        TreeMap<K,V> map = table[hash(key)];
        return map.containsKey(key);
    }

    @Override
    public V get(K key) {
        TreeMap<K,V> map = table[hash(key)];
        return map.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // hash散列表的扩容与缩容
    private void resize(int capacityIndex){
        int oldM = this.m;
        this.m = capacity[capacityIndex];

        TreeMap<K,V>[] newTable = new TreeMap[m];
        for (int i = 0; i < m; i++)
            newTable[i] = new TreeMap<>();

        for (int i = 0; i < oldM; i++){
            TreeMap<K,V> map = table[i];
            for (K key : map.keySet()){
                newTable[hash(key)].put(key,map.get(key));
            }
        }

        this.table = newTable;
    }
}
