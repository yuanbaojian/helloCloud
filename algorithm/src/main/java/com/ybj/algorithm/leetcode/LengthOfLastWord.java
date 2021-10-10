package com.ybj.algorithm.leetcode;

public class LengthOfLastWord {

    public static void main(String[] args) {
        int i = lengthOfLastWord(" password");
        System.out.println("i = " + i);
    }

    public static int lengthOfLastWord(String s){
        int length = s.length();
        int lastWord= 0;
        // 去掉后面的空格
        while (s.charAt(length-1) == ' '){
            length--;
        }
        for (int i = length -1; i >=0 ; i--) {
            if(s.charAt(i)!=' ' ){
                lastWord++;
            } else{
                break;
            }
        }
        return  lastWord;
    }
}
