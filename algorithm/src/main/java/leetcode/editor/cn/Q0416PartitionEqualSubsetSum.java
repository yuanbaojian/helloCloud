package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-08-01 11:17:12 */
public class  Q0416PartitionEqualSubsetSum{    //给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 1425 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0416PartitionEqualSubsetSum().new Solution();
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        //计算综合
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
        }
        //得到平均值
        int avg = sum/2;
        //在这里进行dp方程的计算
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}