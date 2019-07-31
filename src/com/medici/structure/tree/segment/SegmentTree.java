package com.medici.structure.tree.segment;

/**
 * 定义：线段树(区间树)(树状数组数据结构)(平衡二叉树)
 * 结构：线段树并不是一颗满二叉树，也并不是一颗完全二叉树(但是可以使用数组数据结构来表示线段树)
 * 空间：以数组来表示的区间大小为n的线段树需要开辟的最小数组大小为4n
 * 操作：查询线段树,更新线段树
 * 更多：1、查询大区间中的细分子区间的统计和求和，最大值，最小值问题
 *      2、二维，三维区间的子区间的业务问题
 * 时间复杂度：线段树的查询和更新操作的时间复杂度均为O(logn)
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0,0, arr.length - 1);
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }


    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex,int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid + 1,r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    // 返回区间[l, r]的值
    public E query(int l, int r){
        if(l < 0 || l >= data.length ||
            r < 0 || r >= data.length ||
            l > r){
            throw new IllegalArgumentException("Index is Illegal");
        }

        return query(0,0,data.length - 1, l, r);
    }

    // // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex,int l, int r, int queryL, int queryR){
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int mid = l + (r - l) / 2;

        if(queryL >= mid + 1){
            // 查询区间在右孩子表示的区间上
            return query(rightTreeIndex,mid + 1,r,queryL,queryR);
        }else if(queryR <= mid){
            // 查询区间在左孩子表示的区间上
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }else{
            // 查询区间部分在左孩子表示的区间上，部分在右孩子表示的区间上
            E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
            E rightResult = query(rightTreeIndex,mid + 1,r,mid + 1,queryR);
            return merger.merge(leftResult,rightResult);
        }
    }

    // // 将index位置的值，更新为e
    public void set(int index,E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is Illegal");
        }

        data[index] = e;
        set(0,0,data.length - 1,index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex,int l, int r, int index, E e){
        if(l == r){
            tree[treeIndex] = e;
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int mid = l + (r - l) / 2;

        if(index >= mid + 1){
            set(rightTreeIndex,mid + 1, r, index, e);
        }else{
            set(leftTreeIndex, l , mid, index,e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < tree.length; i++){
            if(tree[i] != null){
                result.append(tree[i].toString());
            }else{
                result.append("NULL");
            }

            if(i != tree.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
