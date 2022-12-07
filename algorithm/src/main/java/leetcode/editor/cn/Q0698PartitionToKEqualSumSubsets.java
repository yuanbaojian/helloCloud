package leetcode.editor.cn;
/** * @author  daijiyong * @date    
2022-09-21 08:18:38 */
public class Q0698PartitionToKEqualSumSubsets{    //给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
//
// 
//
// 示例 1： 
//
// 
//输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 示例 2: 
//
// 
//输入: nums = [1,2,3,4], k = 3
//输出: false 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 每个元素的频率在 [1,4] 范围内 
// 
//
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 816 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0698PartitionToKEqualSumSubsets().new Solution();
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;

        int[] nums2 = {1,2,3,4};
        int k2 = 3;

        //操蛋了，没考虑到这点，还是看题解吧
        int[] nums3 ={1,1,1,1,2,2,2,2};
        int k3 = 4;

        boolean b = solution.canPartitionKSubsets(nums3, k3);
        System.out.println("b = " + b);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        //校验 数组和%k == 0
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
        }
        //不能平分，直接提前终止
        if(sum%k!=0){
            return false;
        }
        //计算出平均值
        int avg = sum / k;
        //判断是否能通过平均值进行划分


        // 4, 3, 2, 3, 5, 2, 1
        //    3, 2, 3, 5, 2, 0
        //       0, 3, 5, 2, 0

        //no 采取双指针 ，因为子数组可能不止两个，所以第二个指针要一直加
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            //移除 ==avg的值
            if(temp == avg || temp ==0){
                continue;
            }
            //单个值的递增
            //用过的值要丢掉啊！！！， 不然会出问题，我就不用list来多占空间了  --- 直接赋0吧，还好用
            for (int j = i+1; j < nums.length; j++) {
                if(temp + nums[j] <= avg){
                    temp  += nums[j];
                    nums[j] = 0;
                }
            }
            //对于单个值的递增结果判断，不能满足提前返回
            if(temp != avg){
                return false;
            }
        }

        return true;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
}