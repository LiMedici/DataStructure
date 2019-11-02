package com.medici.structure.sort.linkedsort;

/**
 * 使用归并排序自顶向下实现链表的排序
 */
public class LinkedSort<E> {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public static class Node<E extends Comparable>{
        public E e;
        public Node next;

        public Node(){
            this(null, null);
        }

        public Node(E e){
            this(e,null);
        }

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
    }

    public LinkedSort(E[] arr){
        Node head = new Node();
        Node temp = head;
        for (int i = 0; i < arr.length; i++){
            temp.next = new Node(i);
            temp = temp.next;
        }

        this.root = head.next;
    }

    // 打印以head为头节点的链表信息内容
    public void printNodes(Node node){
        while (node != null){
            System.out.print(node.e);
            if(node.next != null){
                System.out.print(",");
            }
            node = node.next;
        }
    }


    /**
     * 对链表结构进行归并排序,并返回排序后链表的头节点
     */
    public Node startSort(){
        Node result = sort(root);
        this.root = result;
        return result;
    }

    /**
     * 对链表结构进行归并排序,并返回排序后链表的头节点
     */
    public Node sort(Node root){
        if(root == null) return null;
        if(root.next == null) return root;

        Node slow = findMiddle(root);
        Node after = slow.next;
        // 链表切割
        slow.next = null;
        return mergeNode(sort(root),sort(after));
    }

    /**
     * 根据快慢指针，找到中间指针
     * @param root 根节点
     */
    public Node findMiddle(Node root){
        // 根据快慢指针，找到中间节点
        // 快指针
        Node fast = root;
        // 慢指针
        Node slow = root;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 对before指向的连标和after指向的链表，进行归并排序
     * @param before 要合并的一个子链表
     * @param after  要合并的另一个子链表
     */
    private Node mergeNode(Node before,Node after){
        // 新创建一个用于合并的链表,虚拟的头节点
        Node head = new Node();
        Node temp = head;
        while (before != null && after != null){
            if(before.e.compareTo(after.e) < 0){
                temp.next = before;
                before = before.next;
            }else{
                temp.next = after;
                after = after.next;
            }
            temp = temp.next;
        }

        temp.next = (before == null) ? after : before;

        return head.next;
    }

    public void fillInArray(Comparable[] array){
        Node root = this.root;
        for (int i = 0; root != null && i < array.length; i++){
            array[i] = root.e;
            root = root.next;
        }
    }

}
