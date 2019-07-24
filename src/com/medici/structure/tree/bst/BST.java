package com.medici.structure.tree.bst;

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
 * 3、前序非递归方式遍历：使用栈数据结构，因为后进先出原则，先右孩子入栈，再左孩子入栈
 * 4、层序遍历：使用队列数据结构，因为先进先出原则，先左孩子入队，再右孩子入队
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public class Node{
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
     * 二分搜索树的非递归前序遍历
     */
    public void preOrderNR(){
        if(root == null) return;

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
     * 二分搜索树的层序遍历(广度优先遍历)
     */
    public void levelOrder(){
        if(root == null) return;

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

    /**
     * 寻找二分搜索树的最小元素
     */
    public E minimum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty");
        }

        Node minNode = minimum(root);
        return minNode.e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     */
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最大元素
     */
    public E maximum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty");
        }

        Node maxNode = maximum(root);
        return maxNode.e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     */
    private Node maximum(Node node){
        if(node.right == null){
            return node;
        }

        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除元素为e的节点
     */
    public void remove(E e){
        root = remove(root,e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
     * 返回删除节点后新的二分搜索树的根
     */
    private Node remove(Node node, E e){
        if(node == null) return null;

        if(node.e.compareTo(e) > 0){
            // 在左子树上
            node.left = remove(node.left,e);
            return node;
        }else if(node.e.compareTo(e) < 0){
            // 在右子树上
            node.right = remove(node.right,e);
            return node;
        }else{
            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点小的最大节点, 即待删除节点左子树的最大节点
            // 用这个节点顶替待删除节点的位置
            /*Node precursor = maximum(node.left);
            precursor.left = removeMax(node.left);
            precursor.right = node.right;
            node.left = node.right = null;
            return precursor;*/

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 从二分搜索树中删除最小值所在节点, 返回最小值
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }


    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     */
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树中删除最大值所在节点
     */
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }


    /**
     * 删除掉以node为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     */
    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
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
