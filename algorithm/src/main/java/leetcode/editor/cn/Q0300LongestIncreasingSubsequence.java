package leetcode.editor.cn;

import java.util.Arrays;

/** * @author  yuanbaojian * @date    2022-07-04 11:54:04 */
public class Q0300LongestIncreasingSubsequence{    //给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 👍 2599 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0300LongestIncreasingSubsequence().new Solution();
        int[] nums = {2,5,3,7};

        int i = solution.lengthOfLIS(nums);
        System.out.println("i = " + i);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int lengthOfLIS(int[] nums) {
        //每个元素，对应的递增序列长度
        int[] dp = new int[nums.length];
        //每个长度最小为1，不是0
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //递增
                if(nums[i] > nums[j]){
                    //这个就有意思了，解题的关键
                    //需要的是连续递增，所以最好用dp
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        //每个元素，对应的递增序列长度
        int[] dp = new int[nums.length];
        //每个长度最小为1，不是0
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //递增
                if(nums[i] > nums[j]){
                    //这个就有意思了，解题的关键
                    dp[i]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}