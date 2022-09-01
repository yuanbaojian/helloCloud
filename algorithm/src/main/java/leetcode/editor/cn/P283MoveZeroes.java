//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
// 
//
// 示例 2: 
//
// 
//输入: nums = [0]
//输出: [0] 
//
// 
//
// 提示: 
// 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能尽量减少完成的操作次数吗？ 
// Related Topics 数组 双指针 👍 1578 👎 0

package leetcode.editor.cn;

//Java：移动零
public class P283MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();
        int[] nums = {0,1,0,3,12};
        int[] nums2 = {1,0,1};
        solution.moveZeroes(nums2);
        System.out.println("nums = " + nums2);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)l
class Solution {


    public void moveZeroes(int[] nums) {
        if(nums.length ==0){
            return;
        }
        int fast = 0, slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[slow] == 0 && nums[fast] != 0){
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            } else if(nums[slow] != 0){
                slow++;
            }
            fast++;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
