package com.medici.structure.sort.insertion;

/**
 * 插入排序算法(扑克牌玩法)，关联希尔排序算法
 * 一条很重要的性质，插入排序算法内层循环有条件能够提前终止，对于一组近乎有序的数组，
 * 插入排序算法的时间复杂度近乎于O(n)
 */
public class InsertionSort<N extends Comparable> {

    private N[] arr;
    private int n;

    public InsertionSort(N[] arr,int n){
        this.arr = arr;
        this.n = n;
    }

    /**
     * 插入排序算法，没有进行插入排序优化，比较发生位置交换
     */
    public void invokeSort1(){
        for (int i = 1; i < n; i++){
            // 开始对下标为i的位置元素进行排序，i=0的元素已经排好序
            for(int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--){
                // 如果j位置的元素比j-1的位置元素小，则发生交换
                swap(arr,j, j-1);
            }
        }
    }

    /**
     * 插入排序算法优化，在确定arr[i]的位置时候，遇到比arr[i]大的，总是发生交换，交换是需要三次赋值操作的
     * 影响程序运行效率，故算法优化，只用一次赋值来解决问题，大大优化算法的执行效率
     */
    public void invokeSort2(){
        for (int i = 1; i < n; i++){
            // 开始对下标为i的位置元素进行排序，i=0的元素已经排好序
            // 记录arr[j]的元素e
            N e = arr[i];
            int j;
            for(j = i; j > 0 && arr[j - 1].compareTo(e) > 0; j--){
                arr[j] = arr[j - 1];
            }
            // 找到e的位置了
            arr[j] = e;
        }
    }

    // 交互元素
    private void swap(N[] arr,int i, int j){
        N temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
