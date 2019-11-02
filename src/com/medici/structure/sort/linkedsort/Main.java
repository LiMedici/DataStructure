package com.medici.structure.sort.linkedsort;

import com.medici.structure.sort.Action;
import com.medici.structure.sort.TestSortHelper;

public class Main {

    public static void main(String[] args) {
        int n = 10000;
        Integer arr1[] = TestSortHelper.generateIntArray(n,0, n);
        Integer arr2[] = TestSortHelper.copyIntArray(arr1,n);
        TestSortHelper.testSort("Recursion Merge Sort", arr1, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                LinkedSort sort = new LinkedSort(arr);
                sort.startSort();
                // 链表填充进数组
                sort.fillInArray(arr);
            }
        });

        TestSortHelper.testSort("Iteration Merge Sort", arr2, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                LinkedSortBU sort = new LinkedSortBU(arr);
                sort.sort();
                // 链表填充进数组
                sort.fillInArray(arr);
            }
        });
    }
}
