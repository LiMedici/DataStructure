package com.medici.structure.tree.segment;

public class Main {

    public static void main(String[] args) {

        Integer arr[] = new Integer[]{-3,-2,-1,0,1,2,3};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
        int result = segmentTree.query(1,6);
        System.out.println(result);

        segmentTree.set(3,100);

        System.out.println(segmentTree);
    }

}
