package com.medici.structure.tree.uf;

/**
 * 第二版并查集数据结构
 * 应用：解决连接问题
 * 子节点指向父亲节点的树结构
 * 基于rank的优化(不代表集合树的深度，代表集合树深度的等级参照)
 */
public class UnionFind4 implements UF{

    private int[] parent; // parent[i]表示第i个元素所指向的父节点
    private int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for (int i = 0; i < parent.length; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length){
            throw new IllegalArgumentException("Index is illegal");
        }

        while (p != parent[p]){
            p = parent[p];
        }

        return p;
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
