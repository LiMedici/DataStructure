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

    // 寻找arr数组中第k小的元素, k从1开始索引, 即最小元素是第1小元素, 以此类推
    public Comparable solve2(int k){
        return solve( k - 1);
    }

    // 寻找nums数组中第k小的元素
    // 注意: 在我们的算法中, k是从0开始索引的, 即最小的元素是第0小元素, 以此类推
    // 如果希望我们的算法中k的语意是从1开始的, 只需要在整个逻辑开始进行k--即可, 可以参考solve2
    public Comparable solve(int k){
        assert arr != null && k >= 0 && k < arr.length;
        return sort(arr, 0, n - 1, k);
    }

    /**
     * 求出arr[l...r]范围里第k小的数
     */
    private Comparable sort(Comparable[] arr, int l, int r, int k){
        if(l == r) return arr[l];

        // partition之后, nums[p]的正确位置就在索引p上
        int p = partition(arr, l, r);
        if(k > p){
            // 第k小的元素在p右边
            return sort(arr , p + 1, r, k);
        }else if(k < p){
            // 第k小的元素在p左边
            return sort(arr, l , p - 1, k);
        }else{
            return arr[p];
        }
    }

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    // partition 过程, 和快排的partition一样
    // 思考: 双路快排和三路快排的思想能不能用在selection算法中? :)
    private int partition(Comparable[] arr, int l, int r){
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        int pivot = l + random.nextInt(r - l + 1);
        swap(arr, l , pivot);
        Comparable V = arr[l];

        // 使用单路快速排序算法
        // arr[l+1...j] < v arr[j+1...i) > v
        int j = l;
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
        int n = 10000;
        // 生成一个大小为n, 包含0...n-1这n个元素的随机数组arr
        Integer[] arr = TestSortHelper.generateOrderedArray(n);
        TestSortHelper.shuffleArray(arr);

        // 验证Selection.solve, 对arr数组求第i小元素, 应该为i
        Selection selection = new Selection(arr, n);
        for( int i = 0 ; i < n ; i ++ ){
            if(selection.solve(i).compareTo(i) == 0) {
                System.out.println("test " + i + " complete.");
            }
        }
        System.out.println("Test Selection.solve completed.");
        System.out.println();


        arr = TestSortHelper.generateOrderedArray(n);
        TestSortHelper.shuffleArray(arr);

        // 验证Selection.solve2, 对arr数组求第i小元素, 应该为i-1
        // 因为在Selection.solve2中, 第k小元素的k是从1索引的
        for( int i = 0 ; i < n ; i ++ ){
            if(selection.solve2(i).compareTo(i - 1) == 0) {
                System.out.println("test " + i + " complete.");
            }
        }
        System.out.println("Test Selection.solve2 completed.");
    }
}
