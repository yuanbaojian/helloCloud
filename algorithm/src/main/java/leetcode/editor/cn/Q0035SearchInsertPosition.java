package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-08-24 08:41:08 */
public class Q0035SearchInsertPosition{    //给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//
// 请必须使用时间复杂度为 O(log n) 的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,3,5,6], target = 5
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,3,5,6], target = 2
//输出: 1
// 
//
// 示例 3: 
//
// 
//输入: nums = [1,3,5,6], target = 7
//输出: 4
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 为 无重复元素 的 升序 排列数组 
// -10⁴ <= target <= 10⁴ 
// 
// Related Topics 数组 二分查找 👍 1682 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0035SearchInsertPosition().new Solution();
        int[] nums = {1,3,5,6};
        int i = solution.searchInsertV2(nums, 2);
        System.out.println("i = " + i);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        //  这个写得有点丑
        //  1,3,5,6   2
        int ans = -1;
        int l = 0 , r = nums.length-1;
        while (l<=r){
            int mid = l + (r - l)>>2;
            if(mid == nums.length){
                ans = nums.length;
            }
            if(target > nums[mid]){
                l = mid+1;
            } else if (target <= nums[mid]){
                ans = mid;
                r = mid-1;
            }
        }
        return ans;
    }

    public int searchInsertV2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans  = n;
        while (left <= right){
            int mid = left +  ((right-left)>>2);
            if(target <= nums[mid]){
                ans = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}