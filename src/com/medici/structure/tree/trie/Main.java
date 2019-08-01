package com.medici.structure.tree.trie;

import com.medici.structure.set.Set;
import com.medici.structure.tool.FileOperation;
import java.util.ArrayList;

public class Main {

    private static double testTrie(Trie trie, String filename){

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                trie.add(word);
            System.out.println("Total different words: " + trie.getSize());
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        double time = testTrie(trie,"pride-and-prejudice.txt");
        System.out.println("Trie Test Completed! runtime time : " + time);

        System.out.println(trie.isPrefix("prejudice"));
    }

}
