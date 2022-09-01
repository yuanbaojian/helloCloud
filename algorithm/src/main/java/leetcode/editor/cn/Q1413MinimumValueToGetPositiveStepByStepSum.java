package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-08-09 11:25:01 */
public class Q1413MinimumValueToGetPositiveStepByStepSum{    //给你一个整数数组 nums 。你可以选定任意的 正数 startValue 作为初始值。
//
// 你需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。 
//
// 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-3,2,-3,4,2]
//输出：5
//解释：如果你选择 startValue = 4，在第三次累加时，和小于 1 。
//                累加求和
//                startValue = 4 | startValue = 5 | nums
//                  (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
//                  (1 +2 ) = 3  | (2 +2 ) = 4    |   2
//                  (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
//                  (0 +4 ) = 4  | (1 +4 ) = 5    |   4
//                  (4 +2 ) = 6  | (5 +2 ) = 7    |   2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2]
//输出：1
//解释：最小的 startValue 需要是正数。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,-2,-3]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// -100 <= nums[i] <= 100 
// 
// Related Topics 数组 前缀和 👍 64 👎 0
    public static void main(String[] args) {
        Solution solution = new Q1413MinimumValueToGetPositiveStepByStepSum().new Solution();
        int[] array = {-3,2,-3,4,2};
        int i = solution.minStartValueV2(array);
        System.out.println("i = " + i);

    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minStartValue(int[] nums) {
        //  计算前缀和
        //    获得最小前缀和 min
        //    min>0 返回1
        //    min<0 返回1-min
        int minSum = nums[0];
        int[] prefixSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(i==0){
                prefixSum[i] = nums[i];
            } else {
                prefixSum[i] = prefixSum[i-1] + nums[i];
                if(prefixSum[i]<minSum){
                    minSum = prefixSum[i];
                }
            }
        }
        if(minSum>0){
            return 1;
        }
        return 1-minSum;
    }

    public int minStartValueV2(int[] nums) {
        //  计算前缀和
        //    获得最小前缀和 min
        //    min>0 返回1
        //    min<0 返回1-min
        int minSum = 0;
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {

            prefixSum = prefixSum + nums[i];
            if(prefixSum<minSum){
                minSum = prefixSum;
            }
        }
        return 1-minSum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}