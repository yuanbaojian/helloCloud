package leetcode.editor.cn;

import org.jetbrains.annotations.NotNull;

/** * @author  daijiyong * @date
2022-10-07 22:45:46 */
public class Q1800MaximumAscendingSubarraySum{
    //给你一个正整数组成的数组 nums ，返回 nums 中一个 升序 子数组的最大可能元素和。
//
// 子数组是数组中的一个连续数字序列。 
//
// 已知子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，若对所有 i（l <= i < r），numsi < 
//numsi+1 都成立，则称这一子数组为 升序 子数组。注意，大小为 1 的子数组也视作 升序 子数组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,20,30,5,10,50]
//输出：65
//解释：[5,10,50] 是元素和最大的升序子数组，最大元素和为 65 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [10,20,30,40,50]
//输出：150
//解释：[10,20,30,40,50] 是元素和最大的升序子数组，最大元素和为 150 。 
// 
//
// 示例 3： 
//
// 
//输入：nums = [12,17,15,13,10,11,12]
//输出：33
//解释：[10,11,12] 是元素和最大的升序子数组，最大元素和为 33 。 
// 
//
// 示例 4： 
//
// 
//输入：nums = [100,10,1]
//输出：100
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 1 <= nums[i] <= 100 
// 
//
// Related Topics 数组 👍 81 👎 0
    public static void main(String[] args) {
        Solution solution = new Q1800MaximumAscendingSubarraySum().new Solution();
        int[] array = {100,10,1};
        int i = solution.maxAscendingSumV2(array);
        System.out.println(i);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public int maxAscendingSum(int[] nums) {
        //使用双指针做
        int i = 0 , j = 1;
        int sum = 0;

        //10,20,30,40,50
        //0 ,1 ,2 ,3 ,4
        //i     j

        //10,20,30,5,10,50
        // 0,1 ,2 ,3 4 ,5
        // i       j
        //         i j

        //初始化窗口和
        int temp = nums[i];

        //要考虑只有一个元素的影响
        for (; i < nums.length & j<nums.length; j++) {
            //初始值为左指针本身
            //右指针递增，且比较对象递增-1
            if(nums[j]> nums[j-1]){
                temp += nums[j];
            } else{
                //需要重新开启一个递增窗口
                i = j;
                //这里窗口可能溢出
                j = j + 1;
                //新窗口赋值
                //    这里需要加一下 ,不对，大于才能加
                temp = nums[i] + nums[j];
            //    要考虑一个元素的边界
            }
            if(temp > sum){
                sum = temp;
            }
        }
        return sum;
    }

    public int maxAscendingSumV2(int @NotNull [] nums) {
        int sum = 0;
        int temp = nums[0];

        //双指针太过于复杂， 还是用循环吧，考虑下边界问题
        for (int i = 0; i < nums.length; i++) {
            if(temp>sum){
                sum = temp;
            }
            //没越界
            if(i+1 < nums.length){
                //符合递增条件
                if(nums[i+1] > nums[i]){
                    temp += nums[i+1];
                } else {
                    //不符合递增条件，重新确定递增位置

                    //10,20,30,5,10,50
                    // 0,1 ,2 ,3 4 ,5
                    //      i
                    //         i

                    temp = nums[i+1];
                }
                if(temp>sum){
                    sum = temp;
                }
            }
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}