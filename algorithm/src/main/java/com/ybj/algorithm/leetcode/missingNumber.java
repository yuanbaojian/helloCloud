package com.ybj.algorithm.leetcode;

/**
 * @Owner yuanbaojian
 * @Team
 */
public class missingNumber {

    public static void main(String[] args) {

    }

    public int cal(int[] numbers){
        int n = numbers.length;
        int sum = (n * (n + 1) )/2 ;
        for (int i = 0; i < n; i++) {
            sum = sum - numbers[i];
        }
        return sum;
    }
}
