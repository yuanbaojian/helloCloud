package leetcode.editor.cn;

import java.util.*;

/** * @author  yuanbaojian * @date    2022-08-02 14:55:55 */
public class Q0102BinaryTreeLevelOrderTraversal{    //给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 1408 👎 0
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        Solution solution = new Q0102BinaryTreeLevelOrderTraversal().new Solution();
        List<List<Integer>> lists = solution.levelOrder(treeNode);
        System.out.println("lists = " + lists);
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root ==null){
            return result;
        }
        //这个可以取出第一个元素
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            //本层的元素
            List<Integer> level = new ArrayList<>();
            int currentSize = nodeQueue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = nodeQueue.poll();
                level.add(node.val);
                if(Objects.nonNull(node.left)){
                    nodeQueue.offer(node.left);
                }
                if(Objects.nonNull(node.right)){
                    nodeQueue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;    }
}
//leetcode submit region end(Prohibit modification and deletion)
}