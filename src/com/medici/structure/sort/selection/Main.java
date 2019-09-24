package com.medici.structure.sort.selection;

public class Main {
    public static void main(String[] args) {
        int n = 1000;
        Integer arr[] = TestSortHelper.generateIntArray(n,0,n);
        TestSortHelper.testSort("Selection Sort", arr, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                SelectionSort sort = new SelectionSort(arr,n);
                sort.invokeSort();
            }
        });
    }
}
