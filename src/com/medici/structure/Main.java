package com.medici.structure;

import com.medici.structure.linear.Array;

public class Main {

    public static void main(String[] args) {
        Array<Integer> array = new Array<>(5);
        for (int index = 0; index < 5;index ++){
            array.addLast(index);
        }
        System.out.println(array);
    }
}
