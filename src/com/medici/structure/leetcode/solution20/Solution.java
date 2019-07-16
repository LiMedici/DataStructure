package com.medici.structure.leetcode.solution20;

import com.medici.structure.linear.recursion.RecursionLinkedStack;

/**
 * Leetcode 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/description/
 * 括号匹配问题
 * 使用RecursionLinkedStack测试20号问题
 * 该代码主要用于使用Leetcode上的问题测试我们的RecursionLinkedList：）
 */
public class Solution {

    public boolean isValid(String s) {

        RecursionLinkedStack<Character> stack = new RecursionLinkedStack<>();
        for(int i = 0 ; i < s.length() ; i ++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{')
                stack.push(c);
            else{
                if(stack.isEmpty())
                    return false;

                char topChar = stack.pop();
                if(c == ')' && topChar != '(')
                    return false;
                if(c == ']' && topChar != '[')
                    return false;
                if(c == '}' && topChar != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println((new Solution()).isValid("()[]{}"));
        System.out.println((new Solution()).isValid("([)]"));
    }

}
