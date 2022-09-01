package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-06-13 21:49:26 */
public class Q0543DiameterOfBinaryTree{    //给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 👍 1063 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0543DiameterOfBinaryTree().new Solution();
        TreeNode treeNode = new TreeNode(1,new TreeNode(2,new TreeNode(4,new TreeNode(6),null),new TreeNode(5)),new TreeNode(3));
        int i = solution.diameterOfBinaryTree(treeNode);
        System.out.println("i = " + i);

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
    public int diameterOfBinaryTree(TreeNode root) {
        calDiameter(root);
        return maxDiameter;
    }

    int maxDiameter = 0;

    public int calDiameter(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = calDiameter(root.left);
        int right = calDiameter(root.right);
        maxDiameter = Math.max(maxDiameter,left + right);
        return Math.max(left, right) + 1;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
}