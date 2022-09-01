package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-06-10 14:26:10 */
public class Q0104MaximumDepthOfBinaryTree{    //给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1266 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0104MaximumDepthOfBinaryTree().new Solution();
        TreeNode treeNode = new TreeNode(3,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7)));
        int depth = solution.maxDepth(treeNode);
        System.out.println("depth = " + depth);
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
    public int maxDepth(TreeNode root) {
        return calDept(root);
    }

    private int calDept(TreeNode root) {
        if(root == null){
            return 0 ;
        }
        int leftDept = calDept(root.left);
        int rightDept = calDept(root.right);
        return Math.max(leftDept,rightDept) + 1;
    }

    void printIndent(int n){
        for (int i = 0; i < n; i++) {
            System.out.print("  " );
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}