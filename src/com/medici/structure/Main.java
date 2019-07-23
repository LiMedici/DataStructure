package com.medici.structure;

import com.medici.structure.tree.bts.BST;

public class Main {

    public static void main(String[] args) {
        /*ArrayStack<Integer> stack = new ArrayStack<>(5);
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                stack.push(random.nextInt(Integer.MAX_VALUE));
            }else{
                stack.pop();
            }
            System.out.println(stack);
        }
        System.out.println(stack);*/

        /*LoopQueue<Integer> queue = new LoopQueue<>(5);
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                queue.enqueue(random.nextInt(Integer.MAX_VALUE));
            }else{
                queue.dequeue();
            }
            System.out.println(queue);
        }
        System.out.println(queue);*/

        /*LinkedList<Integer> list = new LinkedList<>();
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                list.addLast(random.nextInt(Integer.MAX_VALUE));
            }else{
                list.removeLast();
            }
            System.out.println(list);
        }
        System.out.println(list);*/

        /*LinkedStack<Integer> stack = new LinkedStack<>();
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                stack.push(random.nextInt(Integer.MAX_VALUE));
            }else{
                stack.pop();
            }
            System.out.println(stack);
        }
        System.out.println(stack);*/

        /*LinkedQueue<Integer> queue = new LinkedQueue<>();
        Random random = new Random();
        for (int index = 0; index < 10; index ++){
            if(index % 3 != 2){
                queue.enqueue(random.nextInt(Integer.MAX_VALUE));
            }else{
                queue.dequeue();
            }
            System.out.println(queue);
        }
        System.out.println(queue);*/

        BST<Integer> bst = new BST<>();
        int[] arr = new int[]{1,2,10,9,6,8,4,7,3,5};
        for (int i = 0; i < arr.length; i++){
            bst.add(arr[i]);
        }

        /*bst.preOrder();
        System.out.println("---------------------");
        bst.preOrderNR();
        System.out.println("---------------------");
        bst.levelOrder();
        System.out.println("---------------------");
        bst.inOrder();
        System.out.println("---------------------");
        bst.postOrder();
        System.out.println("---------------------");
        System.out.println(bst);*/

        System.out.println(bst.minimum());
        System.out.println(bst.maximum());

        bst.removeMin();
        System.out.println(bst);

        bst.removeMax();
        System.out.println(bst);

        bst.remove(4);
        System.out.println(bst);
    }

}
