package com.ybj.algorithm.leetcode;

import java.util.Arrays;

public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,2,2,3,4};
        int i = removeDuplicates(nums);
        System.out.println("i = " + i );
        for (int j = 0; j < i; j++) {
            System.out.println("nums = " + nums[j]);
        }
    }

    /**
     * 使用双指针法， 有点迷糊
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        // 使用双指针
        if(nums==null || nums.length == 1){
            return nums.length;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            // 相同 i不变， j++
            if (nums[i] != nums[j]){
            //    不同，要覆盖
                nums[++i]=nums[j];
            }
        }
        return i +1 ;
    }
}
