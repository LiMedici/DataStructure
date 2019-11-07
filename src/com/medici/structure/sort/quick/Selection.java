package com.medici.structure.sort.quick;

import com.medici.structure.sort.TestSortHelper;

import java.util.Random;

public class Selection {

    private Comparable[] arr;
    private int n;
    private Random random = new Random();

    public Selection(Comparable[] arr, int n){
        this.arr = arr;
        this.n = n;
    }

    public Comparable solve(int k){
        return sort(arr, 0, n - 1, k);
    }

    /**
     * 在arr[l...r]中找到第k小的元素，并返回
     */
    private Comparable sort(Comparable[] arr, int l, int r, int k){
        if(l >= r) return l;

        int p = partition(arr, l, r);
        // p就是当前已经排序好的位置
        if(k > p + 1){
            // 第k小的元素在p右边
            return sort(arr , p + 1, r, k);
        }else if(k < p + 1){
            // 第k小的元素在p左边
            return sort(arr, l , p - 1, k);
        }else{
            return arr[p];
        }
    }

    /**
     * 进行partition操作
     */
    private int partition(Comparable[] arr, int l, int r){
        int pivot = l + random.nextInt(r - l + 1);
        swap(arr, l , pivot);
        Comparable V = arr[l];

        // 使用单路快速排序算法
        // arr[l+1...j) < v arr[j,i) > v
        int j = l + 1;
        for (int i = l + 1; i <= r; i++){
            if(arr[i].compareTo(V) < 0){
                j++;
                swap(arr, i , j);
            }
        }

        swap(arr, l, j);
        return j;
    }

    // 交互元素
    private void swap(Comparable[] arr,int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试 Selection
    public static void main(String[] args) {
        // 生成一个大小为n, 包含0...n-1这n个元素的随机数组arr
        int n = 10000;
        Integer[] arr = TestSortHelper.generateOrderedArray(n);
        TestSortHelper.shuffleArray(arr);

        // 验证Selection.solve, 对arr数组求第i小元素, 应该为i
        Selection selection = new Selection(arr, n);
        for( int i = 0 ; i < n ; i ++ ){
            assert selection.solve(i).compareTo(i - 1) == 0;
            System.out.println("test " + i + " complete.");
        }
        System.out.println("Test Selection.solve completed.");
        System.out.println();


        // arr = TestSortHelper.generateOrderedArray(n);
        // TestSortHelper.shuffleArray(arr);

        // 验证Selection.solve2, 对arr数组求第i小元素, 应该为i-1
        // 因为在Selection.solve2中, 第k小元素的k是从1索引的
        /*for( int i = 0 ; i < n ; i ++ ){
            assert selection.solve2(i) == i - 1;
            System.out.println("test " + i + " complete.");
        }*/
        // System.out.println("Test Selection.solve2 completed.");
    }
}
