package com.medici.structure.sort.merge;

import com.medici.structure.sort.TestSortHelper;

import java.util.Arrays;

/**
 * 求arr[0,n)逆序对的个数
 */
public class InversionCount {

    private Comparable[] arr;
    private int n;

    public InversionCount(Comparable[] arr, int n){
        this.arr = arr;
        this.n = n;
    }

    /**
     * 查找逆序对的个数
     * @return 在arr[l...r]中查找逆序对的个数
     */
    public int findInversionCount(int l, int r){
        return solve(arr, l , r);
    }

    /**
     * 开始逆序对的求数
     * 求arr[l..r]范围的逆序数对个数
     * 思考: 归并排序的优化可否用于求逆序数对的算法? :)
     */
    private int solve(Comparable[] arr, int l, int r){
        if(l >= r) return 0;
        int middle = l + (r - l) / 2;
        int res1 = solve(arr, l, middle);
        int res2 = solve(arr, middle + 1, r);
        return res1 + res2 + merge(arr, l , middle , r);
    }

    /**
     * merge函数求出在arr[l...mid]和arr[mid+1...r]有序的基础上, arr[l...r]的逆序数对个数
     */
    private int merge(Comparable[] arr, int l, int middle, int r){
        // 初始化逆序数对个数 res = 0
        int result = 0;
        Comparable[] aux = Arrays.copyOfRange(arr, l , r + 1);
        int i = l;
        int j = middle + 1;

        for (int k = l; k <= r; k++){
            if(i > middle){
                // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j--;
            }else if(j > r){
                // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            }else if(arr[i].compareTo(arr[j]) <= 0){
                // 左半部分所指元素 <= 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            }else{
                // 右半部分所指元素 < 左半部分所指元素
                arr[k] = aux[j - l];
                j++;
                // 此时, 因为右半部分k所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对
                // 左半部分此时未处理的元素个数为 mid - i + 1
                result = result + middle - i + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer arr[] = TestSortHelper.generateIntArray(n,0, n);
        InversionCount count = new InversionCount(arr, n);
        int result = count.findInversionCount(0, n - 1);
        System.out.println("Inversion Count number is : " + result);
    }

}
