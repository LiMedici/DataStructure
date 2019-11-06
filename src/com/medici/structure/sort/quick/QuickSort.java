package com.medici.structure.sort.quick;

import com.medici.structure.sort.insertion.InsertionSort;

import java.util.Random;

/**
 * 快速排序算法
 */
public class QuickSort<N extends Comparable> {

    private N[] arr;
    private int n;

    private Random random = new Random();

    public QuickSort(N[] arr,int n){
        this.arr = arr;
        this.n = n;
    }

    /**
     * 开始快速排序
     */
    public void invokeSort(){
        quickSort(arr, 0, n - 1);
    }

    /**
     * 对arr[l...r]进行一趟快速排序
     */
    private void quickSort(N[] arr , int l, int r){
        // 快速排序的优化，数组元素相对较少，使用插入排序效率最高
        if(r - l + 1 <= 16){
            insertSort(arr, l , r);
            return;
        }

        // 进行partition操作，使得返回的p坐标，保持arr[l...p-1] < v, arr[p+1...r] > v
        int p = partition(arr, l , r);
        quickSort(arr,l, p - 1);
        quickSort(arr,p + 1, r);
    }

    /**
     * 进行partition操作，找到p的坐标，使得返回的p坐标，保持arr[l+1...j] < v, arr[j+1...i-1] > v
     */
    private int partition(N[] arr , int l, int r){
        // 对于接近于有序的数组，如果使用第一个数组位置作为标定为，会退化成链表
        int position = l + random.nextInt(r - l + 1);
        swap(arr, l , position);
        // 使用随机位置作为标定为，数学期望会进行log(n)次递归，但是还是可能退出成链表
        N v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++){
            if(arr[i].compareTo(v) < 0){
                // 把arr[i] 交换到 arr[l...j]区域的末尾
                swap(arr, i , j + 1);
                j++;
            }
        }

        // 交换标准与j的位置
        swap(arr, l , j);
        return j;
    }

    /**
     * 双路快排算法，单路快排算法，一个很鸡肋的问题就是，对于拥有大量重复元素的数组，重复的元素仍然会偏向于一侧
     * 最终会导致分治出来的树的平衡度很低，影响算法性能
     */
    public void invokeSort2(){
        quickSort2(arr, 0 , n - 1);
    }

    /**
     * 对arr[l...r]进行一趟快速排序
     */
    private void quickSort2(N[] arr , int l, int r){
        // 快速排序的优化，数组元素相对较少，使用插入排序效率最高
        if(r - l + 1 <= 16){
            insertSort(arr, l , r);
            return;
        }

        // 进行partition操作，使得返回的p坐标，保持arr[l...p-1] <= v, arr[p+1...r] > v
        int p = partition2(arr, l , r);
        quickSort2(arr,l, p - 1);
        quickSort2(arr,p + 1, r);
    }

    /**
     * 对arr[l...r]进行partition操作，最终保持arr[l+1...i) <= v, arr(j...r] >= v, i > j 快速排序结束
     */
    private int partition2(N[] arr, int l , int r){
        // 对于接近于有序的数组，如果使用第一个数组位置作为标定为，会退化成链表
        int position = l + random.nextInt(r - l + 1);
        swap(arr, l , position);
        // 使用随机位置作为标定为，数学期望会进行log(n)次递归，但是还是可能退出成链表
        N v = arr[l];
        int i = l + 1, j = r;
        while (true){
            while (i <= r && arr[i].compareTo(v) < 0) i++;
            while (j >= l + 1 && arr[j].compareTo(v) > 0) j--;
            if(i > j) break;
            swap(arr, i, j);
            i++;
            j--;
        }

        // 整个循环结束的时候
        // i这个索引停在了从前到后看，第一个>=V的元素的位置
        // j这个索引停在了从后到前看，第一个<=V的元素的位置，整个数组最后一个<=V元素的位置
        swap(arr , l , j);

        return j;
    }



    // 交互元素
    private void swap(N[] arr,int i, int j){
        N temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
}
