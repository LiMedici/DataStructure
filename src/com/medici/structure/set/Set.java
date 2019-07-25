package com.medici.structure.set;

/**
 * Set集合接口方法定义
 */
public interface Set<E> {

    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();

}
