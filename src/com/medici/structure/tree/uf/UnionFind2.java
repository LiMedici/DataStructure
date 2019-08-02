package com.medici.structure.tree.uf;

/**
 * 第二版并查集数据结构 quick-union方式实现
 * 应用：解决连接问题
 * 子节点指向父亲节点的树结构
 *
 * 将每个元素, 看做一个节点
 * 由子节点指向父节点, 根节点指向自身
 * 具有相同根节点的两个节点之间是相连的
 * 两个节点合并时将其中一个节点所在树的根节点指向另一个节点所在树的根节点即可
 *
 * 查询和union操作时间复杂度都是O(h), h表示树高度
 */
public class UnionFind2 implements UF{

    private int[] parent;

    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    public UnionFind2(int size) {
        parent = new int[size];

        for (int i = 0; i < parent.length; i++){
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 返回P节点所在的集合根节点的编号
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

        parent[pRoot] = qRoot;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
