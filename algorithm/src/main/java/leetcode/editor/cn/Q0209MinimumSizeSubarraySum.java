package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-08-12 08:56:46 */
public class Q0209MinimumSizeSubarraySum{    //给定一个含有 n 个正整数的数组和一个正整数 target 。
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 示例 2： 
//
// 
//输入：target = 4, nums = [1,4,4]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 10⁹ 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。 
// 
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 1298 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0209MinimumSizeSubarraySum().new Solution();
        int[] array = {2,3,1,2,4,3};
        int minSubArrayLen = solution.minSubArrayLen(6, array);
        System.out.println("minSubArrayLen = " + minSubArrayLen);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int minSubArrayLen(int target, int[] nums) {
        //[2,3,1,2,4,3]
        //2,5,6,8,12,15

        int prefixSum = 0;
        //双指针法
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        for (int end = 0; end < nums.length; end++) {
            prefixSum+=nums[end];
            while (prefixSum>=target){
                minLength = Math.min(minLength,end-start+1);
                prefixSum-=nums[start];
                start++;
            }
        }
        return minLength==Integer.MAX_VALUE ? 0:minLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}