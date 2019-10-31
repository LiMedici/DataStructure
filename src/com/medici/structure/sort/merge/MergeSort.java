package com.medici.structure.sort.merge;

import com.medici.structure.sort.insertion.InsertionSort;

/**
 * 归并排序，分而治之，时间复杂度O(n2)
 */
public class MergeSort<N extends Comparable> {

    private N[] arr;
    private int n;

    public MergeSort(N[] arr,int n){
        this.arr = arr;
        this.n = n;
    }


    public void invokeSort(){
        int left = 0;
        int right = n - 1;

        invokeSort(arr,left, right);
    }

    /**
     * 对数组arr,[left,right]进行排序
     */
    private void invokeSort(N[] arr, int left, int right){
        // <= 15就已经使用插入排序了
        // if(left >= right) return;

        if(right - left <= 15){
            // 优化2: 对于小规模数组, 使用插入排序
            insertSort(arr,left,right);
            return;
        }

        int middle = left + (right - left) / 2;
        invokeSort(arr,left,middle);
        invokeSort(arr,middle + 1,right);
        // 对[left,right]进行合并操作
        // arr[left,middle] arr[middle+1,right]已经排序完成
        if(arr[middle].compareTo(arr[middle + 1]) > 0) {
            // 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
            // 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
            mergeSort(arr, left, middle, right);
        }
    }

    /**
     * 将arr[left...middle]和arr[middle+1...right]两部分进行归并
     */
    private void mergeSort(N[] arr, int left, int middle,  int right){
        N[] aux = (N[])new Comparable[right - left + 1];
        // copy一份数据到aux数组中
        for (int i = left; i <= right; i++){
            aux[i - left] = arr[i];
        }

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = left;
        int j = middle + 1;
        for (int k = left; k <= right; k++){

            if(i > middle){
                // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - left];
                j++;
            }else if(j > right){
                // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - left];
                i++;
            }else if(aux[i - left].compareTo(aux[j - left]) < 0){
                // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i - left];
                i++;
            }else{
                // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - left];
                j++;
            }
        }
    }

    // 对数组arr[left,right]区域进行插入排序
    private void insertSort(N[] arr, int left, int right){
        for(int i = left + 1; i <= right; i++){

            int j = i;
            N e = arr[j];
            for(; j > left && e.compareTo(arr[j - 1]) < 0; j--){
                arr[j] = arr[j-1];
            }

            arr[j] = e;
        }
    }

    /**
     * 归并排序，自底向上的排序方式
     */
    public void invokeSort2(){
        for (int sz = 1; sz < n; sz *= 2)
            // i 归并排序的两个区域的起始位置，如果第二个区域没有元素，归并排序也将没有意义，所以限制 n - sz
            for (int i = 0; i < n - sz; i += sz+sz) {
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
                mergeSort(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
    }

    /**
     * 归并排序，自底向上的排序方式
     */
    public void invokeSort3(){
        // Merge Sort Bottom Up 优化
        // 对于小数组, 使用插入排序优化
        for( int i = 0 ; i < n ; i += 16 )
            insertSort(arr, i, Math.min(i+15, n-1) );

        for( int sz = 16; sz < n ; sz += sz )
            for( int i = 0 ; i < n - sz ; i += sz+sz )
                // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
                if( arr[i+sz-1].compareTo(arr[i+sz]) > 0 )
                    mergeSort(arr, i, i+sz-1, Math.min(i+sz+sz-1,n-1) );
    }

}
