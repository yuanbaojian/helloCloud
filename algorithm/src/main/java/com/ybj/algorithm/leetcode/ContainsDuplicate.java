package com.ybj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @Owner yuanbaojian
 * @Team
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        boolean containsDuplicate = new ContainsDuplicate().containsDuplicate(nums);
        System.out.println("containsDuplicate = " + containsDuplicate);
    }

    public boolean containsDuplicate(int[] nums) {
        if(nums.length == 0){
            return false;
        }
        Arrays.sort(nums);
        for (int pre = 0; pre < nums.length - 1; pre++) {
            int next = pre + 1;
            if(nums[pre] == nums[next]){
                return true;
            }
        }
        return false;
    }

}
