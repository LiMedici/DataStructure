package com.medici.structure.sort.shell;

/**
 * 希尔排序(缩小增量排序)，插入排序的改进排版，时间复杂度为O(n^3/2)
 * 1.插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
 * 2.但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
 * 3.由于多次插入排序，我们知道一次插入排序是稳定的，不会改变相同元素的相对顺序，但在不同的插入排序过程中，相同的元素可能在各自的插入排序中移动，最后其稳定性就会被打乱，所以shell排序是不稳定的
 */
public class ShellSort<N extends Comparable> {

    private N[] arr;
    private int n;

    public ShellSort(N[] arr,int n){
        this.arr = arr;
        this.n = n;
    }

    /**
     * 希尔排序算法过程
     */
    public void invokeSort(){
        // step 单步进行比较的元素间隔 从 n / 2 -> 1,排序完成
        int step = n;
        while(true){
            // 增量每次减半
            step = step / 2;

            // 插入排序过程
            for (int j = step; j < arr.length; j++){

                for(int k = j - step; k >= 0; k -= step)
                    if(arr[k].compareTo(arr[k + step]) > 0){
                        // 发生元素交换，交换位置
                        swap(arr,k, k + step);
                    }
            }

            if(step == 1){
                break;
            }
        }
    }

    /**
     * 希尔排序算法过程(优化)
     */
    public void invokeSort1(){
        // step 单步进行比较的元素间隔 从 n / 2 -> 1,排序完成
        int step = n;
        while(true){
            // 增量每次减半
            step = step / 2;

            // 插入排序过程
            for (int j = step; j < arr.length; j++){
                N e = arr[j];
                int k;
                for(k = j - step; k >= 0 && arr[k].compareTo(e) > 0; k -= step) {
                    arr[k + step] = arr[k];
                }
                // 注意k位置不满足交换的条件, 所以是k + step , 找到e的位置了
                arr[k + step] = e;
            }

            if(step == 1){
                break;
            }
        }
    }

    /**
     * 希尔排序的优化(liuyubobobo的写法)
     */
    public void invokeSort2(){
        int n = arr.length;

        int h = 1;
        while (h < n/3){
            h = 3 * h + 1;
        }

        while (h >= 1){

            for (int i = h; i < arr.length; i++){

                // 对 arr[i], arr[i-h], arr[i-2*h], arr[i-3*h]... 使用插入排序
                int j = i;
                N e = arr[j];
                for( ; j >= h && e.compareTo(arr[j - h]) < 0; j -= h){
                    arr[j] = arr[j - h];
                }

                arr[j] = e;
            }

            h = h / 3;
        }
    }

    // 交互元素
    private void swap(N[] arr,int i, int j){
        N temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
