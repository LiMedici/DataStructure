package com.medici.structure.tree.bts;

/**
 * 二分搜索树(二叉树，不是一颗满二叉树)
 * 定义: 二叉树的节点大于其左子树的根节点，小于右子树的根节点
 *      二叉搜索树定义不包含重复元素
 */
public class BTS<E extends Comparable<E>> {

    private Node root;
    private int size;

    public class Node<E extends Comparable<E>>{
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

    // 返回二叉搜索树的节点个数
    public int getSize(){
        return size;
    }

    // 返回二叉搜索树是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 向二叉搜索树添加一个元素e
    public Node add(E e){
        return root = add(root,e);
    }

    // 向node为节点的二叉搜索树添加一个元素e
    // 并返回此二叉搜索树的根
    private Node add(Node node,E e){
        if(node == null){
            return new Node(e);
        }

        if(node.e.compareTo(e) == 0){
            return node;
        }

        if(node.e.compareTo(e) > 0){
            node.left = add(node.left,e);
        }else{
            node.right = add(node.right,e);
        }

        return node;
    }

    // 二叉搜索树搜索是否包含某个节点
    public boolean contains(E e){
        return contains(root,e);
    }

    // 以node为根的二叉搜索树搜索是否包含某个节点
    private boolean contains(Node node,E e){
        if(node == null) return false;
        if(node.e.compareTo(e) == 0) return true;

        if(node.e.compareTo(e) > 0){
            return contains(node.left,e);
        }else{
            return contains(node.right,e);
        }
    }

    // 二叉搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null) return;
        System.out.println(node);

        preOrder(node.left);
        preOrder(node.right);
    }

    // 二叉搜索树的中序遍历(应用:排序)
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null) return;

        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }

    // 二叉搜索树的后续遍历(应用:二叉搜索树的内存释放)
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        generatorString(root,builder);
        return builder.toString();
    }

    private void generatorString(Node node,StringBuilder builder){
        if(node == null) return;

        generatorString(node.left,builder);
        builder.append(node.toString());
        generatorString(node.right,builder);
    }
}
