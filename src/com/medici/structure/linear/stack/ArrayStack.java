package com.medici.structure.linear.stack;

import com.medici.structure.linear.array.Array;

/**
 * 动态数组实现栈 FILO First In Last Out
 * 应用：递归，程序调用的系统栈，括号匹配
 */
public class ArrayStack<E> implements Stack<E>{

    private Array<E> array;

    public ArrayStack(){
        this(10);
    }

    public ArrayStack(int capacity) {
        this.array = new Array<>(capacity);
    }

    @Override
    public void push(E e) {
        // 栈顶添加元素
        array.addLast(e);
    }

    @Override
    public E pop() {
        // 栈顶弹出元素
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack: [");
        for (int i = 0; i < array.getSize(); i++){
            stringBuilder.append(array.get(i));
            if(i != array.getSize() - 1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] top");
        return stringBuilder.toString();
    }
}
