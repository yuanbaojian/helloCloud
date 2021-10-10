package com.ybj.algorithm.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlusOne {

    public static void main(String[] args) {
        int[] digits = {9};
        int[] result = plusOne(digits);
        List<Integer> collect = Arrays.stream(result)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("collect.toString() = " + collect.toString());
    }

    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0 ; i--) {
            //进位了
            if(++digits[i] % 10 ==0){
                digits[i] = 0;
            } else{
               return digits;
            }
        }
        //能走到这里的都是 99之类的, 所以加一个1
        digits = new int[length + 1];
        digits[0] = 1;
        return digits;
    }

}
