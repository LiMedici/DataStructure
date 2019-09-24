package com.medici.structure.sort.selection;

import java.util.Random;

/**
 * 排序算法测试类
 */
public class TestSortHelper {
    // 生成一个n个元素的int数组，数组元素范围在[rangeL,rangeR]
    public static Integer[] generateIntArray(int n,int rangeL,int rangeR){
        if(rangeR <= rangeL){
            throw new IllegalArgumentException("rangeR <= rangeL");
        }

        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            arr[i] = random.nextInt(rangeR - rangeL + 1) + rangeL;
        }
        return arr;
    }

    // 测试排序算法的运行时间
    public static void testSort(String sortName,Integer arr[],int n, Action action){
        long startTime = System.nanoTime();
        action.sort(arr,n);
        long endTime = System.nanoTime();
        float result = (float) ((endTime - startTime) / 1000000000.0);
        System.out.println("name: " + sortName + ", runtime :" + result + " s");

        for (int i = 0; i < n; i ++){
            System.out.print(arr[i]);
            if(i != n - 1){
                System.out.print(" ");
            }
        }

        for (int i = 1; i < n; i++){
            if(arr[i-1] > arr[i]){
                System.out.println("sort error");
                break;
            }
        }
    }


}
