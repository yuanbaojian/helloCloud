package leetcode.editor.cn;

import java.util.*;

/** * @author  yuanbaojian * @date    2022-08-02 15:55:24 */
public class Q0103BinaryTreeZigzagLevelOrderTraversal{    //给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
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
// -100 <= Node.val <= 100 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 675 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0103BinaryTreeZigzagLevelOrderTraversal().new Solution();
        TreeNode treeNode = new TreeNode(1, new TreeNode(2,new TreeNode(4),null), new TreeNode(3, null, new TreeNode(5)));
        TreeNode treeNode1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> lists = solution.zigzagLevelOrder(treeNode1);
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Deque<TreeNode> levelNodes = new LinkedList<>();
        levelNodes.add(root);
        boolean fromLeft = true;
        while (!levelNodes.isEmpty()){
            Deque<Integer> nodeValues = new LinkedList<>();
            int currentSize = levelNodes.size();
            for (int i = 0; i < currentSize; i++) {
                //处理本层数据
                TreeNode currentNode = levelNodes.poll();
                if(fromLeft){
                    nodeValues.offerLast(currentNode.val);
                } else {
                    nodeValues.offerFirst(currentNode.val);
                }
                if(currentNode!=null){
                    if(currentNode.left != null){
                        levelNodes.offer(currentNode.left);
                    }

                    if(currentNode.right !=null){
                        levelNodes.offer(currentNode.right);
                    }
                }
            }
            fromLeft = ! fromLeft;
            result.add((List<Integer>) nodeValues);
        }
        return result;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
}