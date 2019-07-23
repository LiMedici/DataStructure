package com.medici.structure.tree.bts;

import com.medici.structure.linear.queue.ArrayQueue;
import com.medici.structure.linear.queue.Queue;
import com.medici.structure.linear.stack.ArrayStack;
import com.medici.structure.linear.stack.Stack;

/**
 * 二分搜索树(二叉树，不是一颗满二叉树) 动态数据结构,具有天然的递归性质
 * 定义: 每一颗子树也是二分搜索树
 * 二分搜索树的节点大于其左子树的根节点，小于右子树的根节点
 * 存储的元素必须有可比较性, Java中的话就要求二分搜索树保存的数据类型要实现Comparable接口, 或者使用额外的比较器实现
 *
 * 一般二分搜索树不包含重复元素, 当然也可以定义包含重复元素的二分搜索树
 *
 * 如果想要包含重复元素的话, 只需要定义二分搜索树的左节点的值小于等于当前节点的值或右节点的值大于等于当前节点即可
 *
 * 遍历分类：
 * 1、深度优先遍历：前序遍历，中序遍历，后序遍历
 * 2、广度优先遍历：层序遍历，按层从左到右进行遍历
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public class Node<E extends Comparable<E>> {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 返回二分搜索树的节点个数
    public int getSize() {
        return size;
    }

    // 返回二分搜索树是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加新的元素e
    public Node add(E e) {
        return root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        // 递归终止条件, node为null, 表示查找到要添加的节点了
        if (node == null) {
            size++;
            return new Node(e);
        }

        // 添加的元素小于当前元素, 向左递归
        if (node.e.compareTo(e) > 0) {
            // 由于递归的add方法的语义是添加新元素, 并返回新的二分搜索树的根节点
            // 所以这里需要使用node.left = add(node.left, e), 接收递归方法返回的值
            node.left = add(node.left, e);
        } else {
            // 添加的元素小于当前元素, 向右递归
            node.right = add(node.right, e);
        }

        // 由于定义的二分搜索树不保存重复元素, 所以针对node.e.compareTo(e) == 0的这种情况这里不做任何处理
        return node;
    }

    // 二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e) {
        if (node == null) return false;
        if (node.e.compareTo(e) == 0) return true;

        if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 前序遍历：
     * 最自然的遍历方式
     * 最常用的遍历方式
     * 对当前节点的遍历在对左右孩子节点的遍历之前, 遍历顺序 : 当前节点->左孩子->右孩子
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.e);

        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历(应用:排序)
     * 对当前节点的遍历在对左右孩子节点的遍历中间, 遍历顺序 : 左孩子->当前节点->右孩子
     * 中序遍历的结果是有序的
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 二分搜索树的后序遍历(应用:二分搜索树的内存释放)
     * 对当前节点的遍历在对左右孩子节点的遍历之后, 遍历顺序 : 左孩子->右孩子->当前节点
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 二分搜索树的前序遍历(非递归写法)
     */
    public void preOrderNR(){
        if(root == null){
            throw new IllegalArgumentException("tree is empty");
        }

        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            // 栈后进先出，所以先压入右孩子
            if(cur.right != null){
                stack.push(cur.right);
            }

            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二叉树的层序遍历(广度优先遍历)
     */
    public void levelOrder(){
        if(root == null){
            throw new IllegalArgumentException("tree is empty");
        }

        Queue<Node> queue = new ArrayQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()){
            Node cur = queue.dequeue();
            System.out.println(cur.e);

            if(cur.left != null){
                queue.enqueue(cur.left);
            }

            if(cur.right != null){
                queue.enqueue(cur.right);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        generatorString(root, builder);
        return builder.toString();
    }

    private void generatorString(Node node, StringBuilder builder) {
        if (node == null) return;

        generatorString(node.left, builder);
        builder.append(node.toString());
        generatorString(node.right, builder);
    }
}
