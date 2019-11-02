package com.medici.structure.sort.linkedsort;

/**
 * 使用归并排序自底向上实现链表的排序
 */
public class LinkedSortBU<E> {
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

    public static class PreTail{
        public Node pre;
        public Node tail;
    }

    public LinkedSortBU(E[] arr){
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
     * 对root为根节点的链表进行归并排序
     */
    public Node sort(){
        Node root = this.root;
        Node dummyHead = new Node();
        dummyHead.next = root;

        int size = 1;
        while (true){
            Node pre = dummyHead, cur = pre;

            while (cur.next != null){
                cur = pre;

                for(int i = 0; i < size && cur.next != null; i++, cur = cur.next);

                if(cur.next != null){
                    Node pre2 = cur;
                    for(int i = 0; i < size && cur.next != null; i++, cur = cur.next);
                    Node next = cur.next, head2 = pre2.next;
                    pre2.next = null;
                    cur.next = null;

                    PreTail recorder = new PreTail();
                    pre.next = merge(pre.next,head2,recorder);

                    pre = recorder.tail;
                    pre.next = next;
                }else if(pre == dummyHead){
                    size = 0;
                    break;
                }
            }

            if(size == 0) break;
            else size *=2;
        }

        return dummyHead.next;
    }

    /**
     * 进行归并操作
     * @param before 需要归并链表1的根节点
     * @param after 需要归并链表2的根节点
     * @param recorder 返回本次操作归并的尾节点
     * @return 返回归并操作之后的链表的头节点
     */
    private Node merge(Node before,Node after,PreTail recorder){
        Node dummyHead = new Node();
        Node temp = dummyHead;
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

        temp.next = before == null? after : before;

        Node tail = temp;

        while (tail.next != null) tail= tail.next;
        recorder.tail = tail;

        return dummyHead.next;
    }

    public void fillInArray(Comparable[] array){
        Node root = this.root;
        for (int i = 0; root != null && i < array.length; i++){
            array[i] = root.e;
            root = root.next;
        }
    }
}
