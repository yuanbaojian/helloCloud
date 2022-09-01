package leetcode.editor.cn;

import java.util.Arrays;

/** * @author  yuanbaojian * @date    2022-06-16 17:26:19 */
public class Q0654MaximumBinaryTree{    //给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
//
// 
// 创建一个根节点，其值为 nums 中的最大值。 
// 递归地在最大值 左边 的 子数组前缀上 构建左子树。 
// 递归地在最大值 右边 的 子数组后缀上 构建右子树。 
// 
//
// 返回 nums 构建的 最大二叉树 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,1,6,0,5]
//输出：[6,3,5,null,2,0,null,null,1]
//解释：递归调用如下所示：
//- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
//    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
//        - 空数组，无子节点。
//        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
//            - 空数组，无子节点。
//            - 只有一个元素，所以子节点是一个值为 1 的节点。
//    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
//        - 只有一个元素，所以子节点是一个值为 0 的节点。
//        - 空数组，无子节点。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[3,null,2,null,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 
// nums 中的所有整数 互不相同 
// 
// Related Topics 栈 树 数组 分治 二叉树 单调栈 👍 445 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0654MaximumBinaryTree().new Solution();
        int []nums = {3,2,1,6,0,5};
        TreeNode treeNode = solution.constructMaximumBinaryTree(nums);
        System.out.println("treeNode = " + treeNode);
    }    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 0){
            return null;
        }

        //找到最大值
        if(nums.length==1){
            return new TreeNode(nums[0]);
        }

        int max = nums[0];
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode treeNode = new TreeNode(max);
        int[] leftNums = Arrays.copyOfRange(nums, 0, maxIndex);
        int[] rightNums = Arrays.copyOfRange(nums, maxIndex+1, nums.length);
        treeNode.left = constructMaximumBinaryTree(leftNums);
        treeNode.right = constructMaximumBinaryTree(rightNums);
        return treeNode;
    }





}
//leetcode submit region end(Prohibit modification and deletion)
}