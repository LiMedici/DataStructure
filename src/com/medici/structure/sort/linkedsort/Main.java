package com.medici.structure.sort.linkedsort;

public class Main {

    public static void main(String[] args) {
        LinkedSort.Node node1 = new LinkedSort.Node<>(98);
        LinkedSort.Node node2 = new LinkedSort.Node<>(34);
        LinkedSort.Node node3 = new LinkedSort.Node<>(43);
        LinkedSort.Node node4 = new LinkedSort.Node<>(11);
        LinkedSort.Node node5 = new LinkedSort.Node<>(44);
        LinkedSort.Node node6 = new LinkedSort.Node<>(54);
        LinkedSort.Node node7 = new LinkedSort.Node<>(12);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        LinkedSort.Node node = node1;
        while(node != null){
            System.out.print(node.e);
            if(node.next != null){
                System.out.print(",");
            }
            node = node.next;
        }
        System.out.println();
        System.out.println("----------------------------------");

        LinkedSort sort = new LinkedSort(node1);
        node = sort.startSort();
        while(node != null){
            System.out.print(node.e);
            if(node.next != null){
                System.out.print(",");
            }
            node = node.next;
        }
    }
}
