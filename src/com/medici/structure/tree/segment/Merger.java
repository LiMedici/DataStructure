package com.medici.structure.tree.segment;

/**
 * 线段树合并接口定义
 */
public interface Merger<E> {
    E merge(E a, E b);
}
