package com.medici.structure.sort.selection;

/**
 * 选择排序算法
 */
public class SelectionSort<N extends Comparable> {

    private N[] arr;
    private int n;

    public SelectionSort(N[] arr,int n){
        this.arr = arr;
        this.n = n;
    }
    // 执行排序操作
    public void invokeSort(){
        for (int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if(arr[j].compareTo(arr[minIndex]) < 0) minIndex = j;
            }

            // 交换操作
            swap(arr,i,minIndex);
        }
    }

    // 交互元素
    private void swap(N[] arr,int i, int j){
        N temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
