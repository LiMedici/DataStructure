package com.medici.structure.sort.quick;

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
     * Quick Sort也是一个O(nlogn)复杂度的算法
     * 可以在1秒之内轻松处理100万数量级的数据
     */
    public void invokeSort(){
        quickSort(arr, 0, n - 1);
    }

    /**
     * 对arr[l...r]进行一趟快速排序
     */
    private void quickSort(N[] arr , int l, int r){
        // 快速排序的优化，对于小规模数组, 使用插入排序效率最高
        if(r - l + 1 <= 16){
            insertSort(arr, l , r);
            return;
        }

        // 对arr[l...r]部分进行partition操作，使得返回的p坐标，使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
        int p = partition(arr, l , r);
        quickSort(arr,l, p - 1);
        quickSort(arr,p + 1, r);
    }

    /**
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     */
    private int partition(N[] arr , int l, int r){
        // 对于接近于有序的数组，如果使用第一个数组位置作为标定为，会退化成链表
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        int pivot = l + random.nextInt(r - l + 1);
        swap(arr, l , pivot);
        // 使用随机位置作为标定点，数学期望会进行log(n)次递归，但是还是可能会退化成链表
        // arr[l+1...j] < v arr[j+1...i) > v
        N v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++){
            if(arr[i].compareTo(v) < 0){
                // 把arr[i] 交换到 arr[l...j]区域的末尾+1
                j++;
                swap(arr, i , j);
            }
        }

        // 交换标定点与j的位置，因为j是最后一个< v的位置
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
        // 快速排序的优化，对于小规模数组, 使用插入排序效率最高
        if(r - l + 1 <= 16){
            insertSort(arr, l , r);
            return;
        }

        // 对arr[l...r]部分进行partition操作，使得返回的p坐标，使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
        int p = partition(arr, l , r);
        quickSort(arr,l, p - 1);
        quickSort(arr,p + 1, r);
    }

    /**
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
     */
    private int partition2(N[] arr, int l , int r){
        // 对于接近于有序的数组，如果使用第一个数组位置作为标定位，会退化成链表
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        int pivot = l + random.nextInt(r - l + 1);
        swap(arr, l , pivot);
        // 使用随机位置作为标定点，数学期望会进行log(n)次递归，但是还是可能会退化成链表
        N v = arr[l];
        // arr[l+1...i) <= v arr(j...r] >= v
        int i = l + 1, j = r;
        while (true){
            // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
            // 思考一下为什么?
            while (i <= r && arr[i].compareTo(v) < 0) i++;
            // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
            // 思考一下为什么?
            while (j >= l + 1 && arr[j].compareTo(v) > 0) j--;
            // 对于上面的两个边界的设定, 有的同学在课程的问答区有很好的回答:)
            // 大家可以参考: http://coding.imooc.com/learn/questiondetail/4920.html
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

    /**
     * 三路快排算法，优化双路快排算法，当数组有很多重复元素的时候，每一次都将重复的元素包含做分割，影响了算法性能
     */
    public void invokeSort3(){
        quickSort3(arr, 0 , n - 1);
    }

    /**
     * 对arr[l...r]进行一趟快速排序
     */
    private void quickSort3(N[] arr , int l, int r){
        // 快速排序的优化，对于小规模数组, 使用插入排序效率最高
        if(r - l + 1 <= 16){
            insertSort(arr, l , r);
            return;
        }

        // 对于接近于有序的数组，如果使用第一个数组位置作为标定位，会退化成链表
        int position = l + random.nextInt(r - l + 1);
        swap(arr, l , position);
        // 使用随机位置作为标定位，数学期望会进行log(n)次递归，但是还是可能退划成链表
        N v = arr[l];

        // 进行partition操作，使得arr[l+1...lt] < v,arr[lt+1,i) == v, arr[gt,r] > v
        int lt = l;
        int gt = r + 1;
        int i = l + 1;

        while(i < gt){
            if(arr[i].compareTo(v) == 0){
                i++;
            }else if(arr[i].compareTo(v) < 0){
                swap(arr,i,lt + 1);
                lt++;
                i++;
            }else if(arr[i].compareTo(v) > 0){
                swap(arr,i,gt - 1);
                gt--;
            }
        }

        swap(arr, l, lt);

        quickSort3(arr,l, lt - 1);
        quickSort3(arr,gt, r);
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
