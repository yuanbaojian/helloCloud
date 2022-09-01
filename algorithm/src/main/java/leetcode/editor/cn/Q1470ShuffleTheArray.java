package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-08-29 09:44:57 */
public class Q1470ShuffleTheArray{    //给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
//
// 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,5,1,3,4,7], n = 3
//输出：[2,3,5,4,1,7] 
//解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
// 
//
// 示例 2： 
//
// 输入：nums = [1,2,3,4,4,3,2,1], n = 4
//输出：[1,4,2,3,3,2,4,1]
// 
//
// 示例 3： 
//
// 输入：nums = [1,1,2,2], n = 2
//输出：[1,2,1,2]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 500 
// nums.length == 2n 
// 1 <= nums[i] <= 10^3 
// 
// Related Topics 数组 👍 112 👎 0
    public static void main(String[] args) {
        Solution solution = new Q1470ShuffleTheArray().new Solution();
        int[] array = {1,1,2,2};
        int n = 2;
        int[] shuffle = solution.shuffle(array, n);
        System.out.println("shuffle = " + shuffle);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int indexX = 0;
        int indexY = n;
        for (int i = 0; i < 2*n; i=i+2) {
            result[i] = nums[indexX];
            result[i+1] = nums[indexY];
            indexX++;
            indexY++;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}