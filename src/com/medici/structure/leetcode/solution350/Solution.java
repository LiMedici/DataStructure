package com.medici.structure.leetcode.solution350;/// Leetcode 350. Intersection of Two Arrays II
/// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/

import java.util.ArrayList;
import java.util.TreeMap;

public class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: nums1){
            if(!map.containsKey(num))
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int num: nums2){
            if(map.containsKey(num)){
                result.add(num);
                map.put(num, map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }

        int[] ret = new int[result.size()];
        for(int i = 0 ; i < result.size() ; i ++)
            ret[i] = result.get(i);

        return ret;
    }
}
