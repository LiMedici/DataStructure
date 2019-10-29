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

    // 选择排序的优化
    // 在每一轮中, 可以同时找到当前未处理元素的最大值和最小值
    public void invokeSort2(){
        int left = 0, right = arr.length - 1;

        // 同时找到最小值和最大值
        while (left < right){

            int minIndex = left;
            int maxIndex = right;

            // 在每一轮查找时, 要保证arr[minIndex] <= arr[maxIndex]
            if(arr[minIndex].compareTo(arr[maxIndex]) > 0){
                swap(arr,minIndex,maxIndex);
            }

            for(int i = left + 1; i < right; i++){
                // 交换位置
                if(arr[i].compareTo(arr[minIndex]) < 0) {
                    minIndex = i;
                }

                if(arr[i].compareTo(arr[maxIndex]) > 0){
                    // 交换位置
                    maxIndex = i;
                }
            }

            swap(arr,minIndex,left);
            swap(arr,maxIndex,right);

            left++;
            right--;
        }
    }

    // 交互元素
    private void swap(N[] arr,int i, int j){
        N temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
