package com.medici.structure.linear.stack;

public interface Stack<E> {

    // 添加一个元素入栈
    void push(E e);
    // 栈顶出栈一个元素
    E pop();
    // 查看栈顶的一个元素
    E peek();
    // 获取栈的容量
    int getCapacity();
    // 获取栈的元素个数
    int getSize();
    // 栈是否为空
    boolean isEmpty();


}
