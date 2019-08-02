package com.medici.structure.tree.uf;

/**
 * 第一版本并查集数据结构 quick-find方式实现
 * 应用：解决连接问题
 *
 * 使用一组数组存储并查集的数据
 * 数组的索引表示数据的编号
 * 数组的值表示数据所属的集合, 具有相同值的数据表示在同一个集合
 *
 * 查询时间复杂度O(1)
 * union时间复杂度O(n)
 */
public class UnionFind1 implements UF{

    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];

        for (int i = 0; i < size; i++){
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p){
        if(p < 0 || p >= id.length){
            throw new IllegalArgumentException("Index is illegal");
        }

        return id[p];
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if(pId == qId) return;

        for (int i = 0; i < id.length; i++){
            if(id[i] == pId) id[i] = qId;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
