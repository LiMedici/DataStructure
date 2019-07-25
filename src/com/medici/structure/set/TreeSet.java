package com.medici.structure.set;

import com.medici.structure.tree.bst.BST;

import java.util.ArrayList;

/**
 * 基于二分搜索树数据结构的高层集合
 * TreeSet add remove method 时间复杂度均为O(h) == O(logn)
 * 缺点，二分搜索树当添加有序数据，退化为链表，时间复杂度O(n)
 * 改善:平衡二叉树(例如红黑树)
 */
public class TreeSet<E extends Comparable<E>> implements Set<E>{

    private BST bst;

    public TreeSet() {
        this.bst = new BST();
    }


    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            TreeSet<String> set1 = new TreeSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            TreeSet<String> set2 = new TreeSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
