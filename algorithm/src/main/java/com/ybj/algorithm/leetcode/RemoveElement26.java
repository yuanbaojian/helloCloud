package com.ybj.algorithm.leetcode;

public class RemoveElement26 {

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int length = removeElement(nums, 3);
        for (int i = 0; i < length; i++) {
            System.out.println("nums[i] = " + nums[i]);
        }
    }

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if( nums[i] != nums[j] && nums[i] == val){
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
