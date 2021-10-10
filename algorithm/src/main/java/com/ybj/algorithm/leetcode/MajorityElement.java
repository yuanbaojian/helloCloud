package com.ybj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @Owner yuanbaojian
 * @Team
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2,3,3};
        int i = new MajorityElement().majorityElement(nums);
        System.out.println("i = " + i);
    }

    int majorityElement(int[] nums){
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
