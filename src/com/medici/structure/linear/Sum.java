package com.medici.structure.linear;

/**
 * 最小的子问题求解，组合成原问题的问题
 * 代价：函数调用+系统栈空间
 */
public class Sum {

    public static int sum(int[] arr){
        return sum(arr,0);
    }

    // 递归函数的宏观语意
    // 计算arr[l...n)范围里的数字和
    private static int sum(int[] arr,int l){
        // 求解最基本问题
        if(l == arr.length) return 0;
        // 把原问题转换为更小的问题
        /*int x = sum(arr,l + 1);
        int result = arr[l] + x;
        return result;*/
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(sum(arr));
    }

}
