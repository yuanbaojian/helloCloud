package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/** * @author  yuanbaojian * @date    2022-08-02 14:28:31 */
public class Q0144BinaryTreePreorderTraversal{    //给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 877 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0144BinaryTreePreorderTraversal().new Solution();
        TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> integers = solution.preorderTraversal(treeNode);
        System.out.println("integers = " + integers);
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        getNode(root,result);
        return result;
    }

    private void getNode(TreeNode root, List<Integer> result) {
        if(root!=null){
            getNode(root.left,result);
            getNode(root.right,result);
            //这个在哪就是 什么  前中后序遍历
            result.add(root.val);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}