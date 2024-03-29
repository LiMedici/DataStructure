package com.medici.structure.sort;

import java.util.Arrays;
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

    /**
     * 生成一个近有序的数组，发生swapTimes次数交换
     */
    public static Integer[] generateNearlyOrderedIntArray(int n, int swapTimes){
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++){
            arr[i] = i;
        }

        Random random = new Random();
        for (int i = 0; i < swapTimes; i++) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            //发生交换
            swap(arr,x,y);
        }

        return arr;
    }

    // 生成一个完全有序的数组
    public static Integer[] generateOrderedArray( int n ){

        return generateNearlyOrderedIntArray(n, 0);
    }

    // 生成一个完全逆序的数组
    public static Integer[] generateInversedArray( int n ){

        Integer[] arr = generateOrderedArray( n );
        for( int i = n/2 - 1 ; i >= 0 ; i -- ){
            Integer t = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = t;
        }
        return arr;
    }

    // 将数组arr随机化
    public static void shuffleArray( Comparable[] arr ){

        int n = arr.length;
        for( int i = 0 ; i < n ; i ++ ){
            int j = (int)(Math.random() * (n-i)) + i;

            Comparable t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

    /**
     * 判断是否完全有序
     */
    public static boolean isOrdered( Comparable[] arr ){
        int diff = 0;
        for (int i = 1; i < arr.length; i++){
            int newDiff = arr[i - 1].compareTo(arr[i]);
            if(diff + newDiff == 0){
                return false;
            }
        }

        return true;
    }

    // copy一个int数组
    public static int[] copyIntArray(int[] arr, int n){
        return Arrays.copyOf(arr,n);
    }

    // copy一个int数组
    public static Integer[] copyIntArray(Integer[] arr, int n){
        Integer dest[] = new Integer[n];
        for (int i = 0; i < n; i++) {
            dest[i] = new Integer(arr[i]);
        }

        return dest;
    }

    // 交互元素
    public static void swap(Integer[] arr,int i, int j){
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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

        System.out.println();
        for (int i = 1; i < n; i++){
            if(arr[i-1] > arr[i]){
                System.out.println("sort error");
                break;
            }
        }
    }


}
