package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-06-16 14:39:08 */
public class Q0114FlattenBinaryTreeToLinkedList{    //给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
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
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1212 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0114FlattenBinaryTreeToLinkedList().new Solution();
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(3),new TreeNode(4)),new TreeNode(5,null,new TreeNode(6)));
        TreeNode treeNode1 = new TreeNode(1,new TreeNode(2),null);
        solution.flatten(treeNode1);
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
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        //后续遍历
        flatten(leftNode);
        flatten(rightNode);
        // 右子树 移到左子树下最下面
        TreeNode tempLeft = leftNode;
        if(leftNode == null){
            return;
        }
        while(tempLeft !=null && tempLeft.right!=null){
            tempLeft = tempLeft.right;
        }
        tempLeft.right = rightNode;
        // 左子树 变成右子树
        root.right = leftNode;
        root.left = null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}