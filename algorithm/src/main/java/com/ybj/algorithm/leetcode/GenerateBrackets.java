package com.ybj.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

public class GenerateBrackets {
}


class Solution {

    List<String> list = new LinkedList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n,n,"");
        System.out.println("list = " + list);
        return list;
    }

    public void dfs(int left , int right , String currentString){
        if( left == 0 && right ==0 ){
            list.add(currentString);
            return;
        }

        if(left > 0){
            dfs(left-1,right,currentString+"(");
        }

        if(right > left){
            dfs(left,right-1,currentString+")");
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generateParenthesis(2);
    }

}