package com.medici.structure.tree.uf;

/**
 * 并查集功能定义接口
 */
public interface UF {

    // 返回并查集节点数量
    int getSize();
    // 将节点p和节点q连接在同一集合上
    void unionElements(int p, int q);
    // 节点p和节点q是否连接
    boolean isConnected(int p, int q);

}
