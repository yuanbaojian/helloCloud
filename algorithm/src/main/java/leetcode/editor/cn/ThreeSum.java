package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length-2; i++) {
            int left = i+1;
            int right = nums.length - 1;
            int target = -nums[i];
            // 需要去重, 如果有一个target一样，那肯定就是重复的，不需要再找第二次
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            while(left<right){

                int sum = nums[left] + nums[right];
                if (sum == target){
                    List<Integer> temp = new LinkedList<>();
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    temp.add(nums[i]);
                    if(!result.contains(temp)){
                        result.add(temp);
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++ ;
                } else{
                    right--;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        ThreeSum three = new ThreeSum();
        List<List<Integer>> lists = three.threeSum(nums);
        System.out.println("lists = " + lists);
    }

}
