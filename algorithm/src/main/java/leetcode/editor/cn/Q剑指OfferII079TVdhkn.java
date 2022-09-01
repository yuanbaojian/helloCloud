package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

/** * @author  yuanbaojian * @date    2022-08-26 11:08:00 */
public class Q剑指OfferII079TVdhkn{    //给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
//
// 
//
// 注意：本题与主站 78 题相同： https://leetcode-cn.com/problems/subsets/ 
// Related Topics 位运算 数组 回溯 👍 44 👎 0
    public static void main(String[] args) {
        Solution solution = new Q剑指OfferII079TVdhkn().new Solution();
        int[] array = {1,2,3};
        List<List<Integer>> subsets = solution.subsets(array);
        System.out.println("subsets = " + subsets);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        result.add(new LinkedList<>());
        dfs(result,nums,0);
        return result;
    }

    public void dfs (List<List<Integer>> result, int[] nums, int i){
        //1
        //2 | 1，2
        //3 | 1,3| 2，3 | 1，2，3
        //4 |1,4| 2,4| 1,2,4|  3，4 | 1，3,4| 2,3，4 | 1,2,3,4
        //终止条件
        if(i>=nums.length){
            return;
        }
        //加入当前单个元素
        LinkedList<Integer> current = new LinkedList<>();
        current.add(nums[i]);
        result.add(current);
        //遍历之前的列表
        List<List<Integer>> beforeResult = new LinkedList<>();
        for (int j = 1; j < result.size()-1; j++) {
            List<Integer> before = new LinkedList<>();
            before.addAll(result.get(j));
            before.add(nums[i]);
            beforeResult.add(before);
        }
        System.out.println("  before adding  "  );
        if(!beforeResult.isEmpty()){
            result.addAll(beforeResult);
        }
        //递归生成下个组合
        dfs(result,nums,i+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}