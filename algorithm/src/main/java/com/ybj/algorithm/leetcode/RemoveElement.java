package com.ybj.algorithm.leetcode;

public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int i = removeElement(nums, 3);
        System.out.println("i = " + i);
    }

    public static int removeElement(int[] nums, int val) {
        int size = nums.length;
        if(size == 0){
            return 0;
        }
        int left = 0;
        for (int right = 0; right < size; right++) {
            if(nums[right] != val){
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

}
