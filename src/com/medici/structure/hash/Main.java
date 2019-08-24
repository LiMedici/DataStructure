package com.medici.structure.hash;

import com.medici.structure.map.AVLMap;
import com.medici.structure.map.TreeMap;
import com.medici.structure.tool.FileOperation;
import com.medici.structure.tree.rb.RBTree;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            TreeMap<String, Integer> bst = new TreeMap<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.put(word, bst.get(word) + 1);
                else
                    bst.put(word, 1);
            }

            for(String word: words)
                bst.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();

            AVLMap<String, Integer> avl = new AVLMap<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.put(word, avl.get(word) + 1);
                else
                    avl.put(word, 1);
            }

            for(String word: words)
                avl.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");


            // Test RBTree
            startTime = System.nanoTime();

            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, rbt.get(word) + 1);
                else
                    rbt.add(word, 1);
            }

            for(String word: words)
                rbt.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");


            // Test HashTable
            startTime = System.nanoTime();

            HashTable<String, Integer> ht = new HashTable<>();
            for (String word : words) {
                if (ht.contains(word))
                    ht.put(word, ht.get(word) + 1);
                else
                    ht.put(word, 1);
            }

            for(String word: words)
                ht.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");
        }

        System.out.println();
    }
}
