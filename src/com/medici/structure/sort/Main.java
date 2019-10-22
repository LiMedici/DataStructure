package com.medici.structure.sort;

import com.medici.structure.sort.insertion.InsertionSort;
import com.medici.structure.sort.selection.SelectionSort;

public class Main {
    public static void main(String[] args) {
        int n = 10000;
        Integer arr1[] = TestSortHelper.generateNearlyOrderedIntArray(n,100);
        Integer arr2[] = TestSortHelper.copyIntArray(arr1,n);
        TestSortHelper.testSort("Selection Sort", arr1, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                SelectionSort sort = new SelectionSort(arr,n);
                sort.invokeSort();
            }
        });

        TestSortHelper.testSort("Insertion Sort", arr2, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                InsertionSort sort = new InsertionSort(arr,n);
                sort.invokeSort2();
            }
        });
    }
}
