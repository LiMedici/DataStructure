package com.medici.structure.tree.heap;

import com.medici.structure.tree.heap.min.MinHeap;

import java.util.Random;

public class Main {

    private static double testHeap(Integer[] testData, boolean isHeapify){

        long startTime = System.nanoTime();

        MinHeap<Integer> minHeap;
        if(isHeapify)
            minHeap = new MinHeap<>(testData);
        else{
            minHeap = new MinHeap<>();
            for(int num: testData)
                minHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for(int i = 0 ; i < testData.length ; i ++)
            arr[i] = minHeap.extractMin();

        for(int i = 1 ; i < testData.length ; i ++)
            if(arr[i-1] > arr[i])
                throw new IllegalArgumentException("Error");

        printArr(arr);
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    private static void printArr(int[] arr){
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i]);
            if(i != arr.length - 1){
                result.append(",");
            }
        }
        result.append("]");
        System.out.println(result);
    }

    public static void main(String[] args) {

        int n = 1000;

        Random random = new Random();
        Integer[] testData = new Integer[n];
        for(int i = 0 ; i < n ; i ++)
            testData[i] = random.nextInt(n);

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }

}
