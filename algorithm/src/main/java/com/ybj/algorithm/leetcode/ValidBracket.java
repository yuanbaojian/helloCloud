package com.ybj.algorithm.leetcode;

import lombok.Builder;

import java.util.Objects;
import java.util.Stack;

@Builder
public class ValidBracket {

    public static void main(String[] args) {
        boolean valid = ValidBracket.builder().build().isValid("{[]}");
        System.out.println("valid = " + valid);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 很巧妙的用右括号代替
            if( c == '('){
                stack.push(')');
            } else if( c =='['){
                stack.push(']');
            } else if( c =='{'){
                stack.push('}');
            } else if(stack.isEmpty() || c!=stack.pop()){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s){
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
            Character top = stack.peek();
            if(Objects.nonNull(top)){
                if( c == '(' && top== ')'){
                    stack.pop();
                }
                if( c == '[' && top== ']'){
                    stack.pop();
                }
                if( c == '{' && top== '}'){
                    stack.pop();
                }

            }
        }
        return stack.isEmpty();
    }
}
