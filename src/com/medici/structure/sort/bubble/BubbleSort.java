package com.medici.structure.sort.bubble;

/**
 * 冒泡排序，稳定的排序算法
 * 1、近似有序的数据，冒泡排序的时间复杂度为O(n)
 * 2、将最小最大的数找到在数据的一端来实现排序，类似吐泡泡
 */
public class BubbleSort<N extends Comparable> {

    private N[] arr;
    private int n;

    public BubbleSort(N[] arr,int n){
        this.arr = arr;
        this.n = n;
    }

    public void invokeSort1(){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                // 左右相邻近的元素相比较，如果arr[j - 1] > arr[j],交换位置
                if(arr[j].compareTo(arr[j + 1]) > 0){
                    // 交换位置
                    swap(arr,j, j+1);
                }
            }
        }
    }

    /**
     * 1.对于已经排好序的数组，并不能提前终止循环，需要再优化是否已经排好序的标记
     */
    public void invokeSort2(){
        for(int i = 0; i < arr.length; i++){
            // 有序标记，每一轮的初始值都为true
            boolean isSorted = true;
            for(int j = 0; j < arr.length - i - 1; j++){
                // 左右相邻近的元素相比较，如果arr[j - 1] > arr[j],交换位置
                if(arr[j].compareTo(arr[j + 1]) > 0){
                    // 交换位置
                    swap(arr,j, j + 1);
                    // 因为有元素交换，所以不是有序的，标记为false
                    isSorted = false;
                }
            }

            if(isSorted) break;
        }
    }

    /**
     * 1.对于已经排好序的数组，并不能提前终止循环，需要再优化是否已经排好序的标记
     * 2.有序区的长度和排序的轮数是相等的，实际上，数列真正的有序区长度可能大于排序的轮数
     */
    public void invokeSort3(){
        int sortBorder = arr.length - 1;
        for(int i = 0; i < arr.length; i++){
            boolean isSorted = true;

            final int border = sortBorder;
            for(int j = 0; j < border; j++){
                // 左右相邻近的元素相比较，如果arr[j - 1] > arr[j],交换位置
                if(arr[j].compareTo(arr[j + 1]) > 0){
                    // 交换位置
                    swap(arr,j, j + 1);
                    isSorted = false;
                    // 把无序数列的边界更新为最后一次交换元素的位置
                    sortBorder = j;
                }
            }

            if(isSorted) break;
        }
    }

    public void invokeSort4(){
        int n = arr.length;
        boolean swapped = false;

        do{
            swapped = false;
            for(int i = 1; i < n; i++){
                // 左右相邻近的元素相比较，如果arr[i] > arr[i + 1],交换位置
                if(arr[i - 1].compareTo(arr[i]) > 0){
                    // 交换位置
                    swap(arr,i - 1, i);
                    swapped = true;
                }
            }

            // 优化, 每一趟Bubble Sort都将最大的元素放在了最后的位置
            // 所以下一次排序, 最后的元素可以不再考虑
            n--;
        }while (swapped);
    }

    public void invokeSort5(){
        int n = arr.length;
        // 使用newn进行优化
        int newn;

        do{
            newn = 0;
            for( int i = 1 ; i < n ; i ++ )
                if( arr[i-1].compareTo(arr[i]) > 0 ){
                    swap( arr , i-1 , i );

                    // 记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
                    newn = i;
                }
            n = newn;
        }while(newn > 0);
    }

    // 交互元素
    private void swap(N[] arr,int i, int j){
        N temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
