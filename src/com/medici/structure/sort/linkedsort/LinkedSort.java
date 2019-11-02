package com.medici.structure.sort.linkedsort;

public class LinkedSort {
    private Node root;

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

    public LinkedSort(Node root){
        this.root = root;
    }


    /**
     * 对链表结构进行归并排序,并返回排序后链表的头节点
     */
    public Node startSort(){
        return sort(root);
    }

    /**
     * 对链表结构进行归并排序,并返回排序后链表的头节点
     */
    public Node sort(Node root){
        if(root == null) return null;
        if(root.next == null) return root;

        Node slow = findMiddle(root);
        Node after = slow.next;
        // 第一个链表的终止条件
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

}
