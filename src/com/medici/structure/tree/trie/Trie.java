package com.medici.structure.tree.trie;

import java.util.HashMap;

/**
 * 字典树，前缀树
 */
public class Trie {

    private class Node{
        public boolean isWord;
        public HashMap<Character,Node> next;

        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new HashMap<>();
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word){

        Node cur = root;

        for (int index = 0; index < word.length(); index++){
            char c = word.charAt(index);
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }

            cur = cur.next.get(c);
        }

        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){
        Node cur = root;

        for (int index = 0; index < word.length(); index++){
            char c = word.charAt(index);
            if(cur.next.get(c) == null) return false;
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node cur = root;

        for (int index = 0; index < prefix.length(); index++){
            char c = prefix.charAt(index);
            if(cur.next.get(c) == null) return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
