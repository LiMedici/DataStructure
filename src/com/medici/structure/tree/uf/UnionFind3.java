package com.medici.structure.tree.uf;

/**
 * 第二版并查集数据结构
 * 应用：解决连接问题
 * 子节点指向父亲节点的树结构
 *
 * 将每个元素, 看做一个节点
 * 由子节点指向父节点, 根节点指向自身
 * 具有相同根节点的两个节点之间是相连的
 * 两个节点合并时将其中一个节点所在树的根节点指向另一个节点所在树的根节点即可
 *
 * 基于size的优化
 */
public class UnionFind3 implements UF{

    private int[] parent; // parent[i]表示第一个元素所指向的父节点
    private int[] sz;     // sz[i]表示以i为根的集合中元素个数

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for (int i = 0; i < parent.length; i++){
            parent[i] = i;
            sz[i] = 1;
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

        if(pRoot == qRoot) return;

        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if(sz[pRoot] > sz[qRoot]){
            parent[qRoot] = pRoot;
            sz[pRoot] = sz[pRoot] + sz[qRoot];
        }else{ // sz[pRoot] <= sz[qRoot];
            parent[pRoot] = qRoot;
            sz[qRoot] = sz[qRoot] + sz[pRoot];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
