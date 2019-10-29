package com.medici.structure.sort.cocktail;

/**
 * 鸡尾酒排序,冒泡排序的算法优化
 * 元素的比较和位置交换是双向的，对于近乎有序的数组排序效率很高，弥补了冒泡排序有序临界区 > 交换轮数的缺陷
 */
public class CocktailSort<N extends Comparable> {
    private N[] arr;
    private int n;

    public CocktailSort(N[] arr,int n){
        this.arr = arr;
        this.n = n;
    }


    public void invokeSort(){
        // 外层循环，双向进行元素比较和交换
        for(int i = 0; i < arr.length / 2; i++){

            boolean isSorted = true;

            // 奇数排序
            for(int j = i; j < arr.length - 1 - i; j++){
                if(arr[j].compareTo(arr[j + 1]) > 0){
                    // 发生交换
                    swap(arr, j, j + 1);
                    isSorted = false;
                }
            }

            if(isSorted) break;

            isSorted = true;

            // 偶数排序
            for(int j = arr.length - 1; j > i; j--){
                if(arr[j].compareTo(arr[j - 1]) < 0){
                    //发生交换
                    swap(arr, j, j -1);
                    isSorted = false;
                }
            }

            if(isSorted) break;

        }
    }

    // 交互元素
    private void swap(N[] arr,int i, int j){
        N temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
