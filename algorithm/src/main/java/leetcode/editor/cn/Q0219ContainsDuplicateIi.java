package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/** * @author  yuanbaojian * @date    2022-08-23 20:51:30 */
public class Q0219ContainsDuplicateIi{    //给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i
//- j) <= k 。如果存在，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1], k = 3
//输出：true 
//
// 示例 2： 
//
// 
//输入：nums = [1,0,1,1], k = 1
//输出：true 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3,1,2,3], k = 2
//输出：false 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 0 <= k <= 10⁵ 
// 
// Related Topics 数组 哈希表 滑动窗口 👍 507 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0219ContainsDuplicateIi().new Solution();
        int[] nums = {1,2,3,1,2,3};
        boolean b = solution.containsNearbyDuplicateV2(nums, 2);
        System.out.println(b);

    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int l = 0; l < nums.length; l++) {
            for (int r = 1; r <= k && l+r<nums.length; r++) {
                if(nums[l] == nums[l+r]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicateV2(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            Integer preIndex = map.get(nums[index]);
            if(preIndex == null){
                map.put(nums[index],index);
            } else {
                if(Math.abs(index - preIndex) <= k){
                    return true;
                } else{
                    map.put(nums[index],index);
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}