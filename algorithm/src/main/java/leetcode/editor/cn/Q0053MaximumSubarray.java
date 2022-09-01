package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-07-15 11:56:06 */
public class Q0053MaximumSubarray{    //给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 子数组 是数组中的一个连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [5,4,-1,7,8]
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 👍 5104 👎 0
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums1 = {5,4,-1,7,8};
        int[] nums2 = {-1,-2};
        Solution solution = new Q0053MaximumSubarray().new Solution();
        int i = solution.maxSubArray2(nums2);
        System.out.println("i = " + i);

    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        int[] indexSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(i==0){
                indexSum[0]= nums[i];
            } else {
                indexSum[i] = indexSum[i-1] + nums[i];
            }
        }
        int max = indexSum[indexSum.length-1];
        for (int i = 0; i < indexSum.length; i++) {
            if(indexSum[i]>max){
                max = indexSum[i];
            }
            //这样子是n^2, 会超时的
            for (int j = indexSum.length-1; j >i; j--) {
                int temp = indexSum[j] - indexSum[i];
                if(temp>max){
                    max = temp;
                }
            }
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        //初始化dpArray
        int[] dpArray = new int[nums.length];
        //第一位直接赋值
        dpArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpArray[i] = Math.max(nums[i],nums[i]+ dpArray[i-1]);
        }
        int max = dpArray[0];
        for (int i = 0; i < dpArray.length; i++) {
            if(dpArray[i]>max){
                max = dpArray[i];
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}