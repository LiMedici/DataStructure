package com.medici.structure.sort;

import com.medici.structure.sort.bubble.BubbleSort;
import com.medici.structure.sort.cocktail.CocktailSort;
import com.medici.structure.sort.insertion.InsertionSort;
import com.medici.structure.sort.selection.SelectionSort;
import com.medici.structure.sort.shell.ShellSort;

public class Main {
    public static void main(String[] args) {
        int n = 10000;
        Integer arr1[] = TestSortHelper.generateIntArray(n,0, n);
        Integer arr2[] = TestSortHelper.copyIntArray(arr1,n);
        Integer arr3[] = TestSortHelper.copyIntArray(arr1,n);
        Integer arr4[] = TestSortHelper.copyIntArray(arr1,n);
        Integer arr5[] = TestSortHelper.copyIntArray(arr1,n);
        TestSortHelper.testSort("Selection Sort", arr1, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                SelectionSort sort = new SelectionSort(arr,n);
                sort.invokeSort2();
            }
        });

        TestSortHelper.testSort("Insertion Sort", arr2, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                InsertionSort sort = new InsertionSort(arr,n);
                sort.invokeSort2();
            }
        });

        TestSortHelper.testSort("Bubble Sort", arr3, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                BubbleSort sort = new BubbleSort(arr,n);
                sort.invokeSort4();
            }
        });

        TestSortHelper.testSort("Cocktail Sort", arr4, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                CocktailSort sort = new CocktailSort(arr,n);
                sort.invokeSort();
            }
        });

        TestSortHelper.testSort("Shell Sort", arr5, n, new Action() {
            @Override
            public void sort(Integer[] arr, int n) {
                ShellSort sort = new ShellSort(arr,n);
                sort.invokeSort2();
            }
        });
    }
}
