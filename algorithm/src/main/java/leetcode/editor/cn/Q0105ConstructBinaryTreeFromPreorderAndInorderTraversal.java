package leetcode.editor.cn;

import java.util.Arrays;

/** * @author  yuanbaojian * @date    2022-06-17 18:10:41 */
public class Q0105ConstructBinaryTreeFromPreorderAndInorderTraversal{    //给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1637 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        int [] preorder = {3,9,20,15,7}, inorder = {9,3,15,20,7};
        TreeNode treeNode = solution.buildTree(preorder, inorder);
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0){
            return null;
        }
        //构建根节点
        TreeNode root = new TreeNode(preorder[0]);
        //左子树
        int inorderRootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i] == preorder[0]){
                inorderRootIndex = i;
            }
        }
        int[] inorderLeft = Arrays.copyOfRange(inorder, 0, inorderRootIndex);
        int[] inorderRight = Arrays.copyOfRange(inorder, inorderRootIndex+1, inorder.length);

        int[] preorderLeft = Arrays.copyOfRange(preorder, 1,inorderLeft.length+1 );
        int[] preorderRight = Arrays.copyOfRange(preorder, inorderLeft.length+1,preorder.length );

        root.left = buildTree(preorderLeft,inorderLeft);
        root.right = buildTree(preorderRight,inorderRight);

        //右子树
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}