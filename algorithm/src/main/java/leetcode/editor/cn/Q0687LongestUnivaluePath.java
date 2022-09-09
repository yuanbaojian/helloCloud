package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-09-02 08:45:53 */
public class Q0687LongestUnivaluePath{    //给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
//
// 两个节点之间的路径长度 由它们之间的边数表示。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：root = [5,4,5,1,1,5]
//输出：2
// 
//
// 示例 2: 
//
// 
//
// 
//输入：root = [1,4,5,4,4,5]
//输出：2
// 
//
// 
//
// 提示: 
//
// 
// 树的节点数的范围是 [0, 10⁴] 
// -1000 <= Node.val <= 1000 
// 树的深度将不超过 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 614 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0687LongestUnivaluePath().new Solution();
        TreeNode treeNode = new TreeNode(5,new TreeNode(4,new TreeNode(1),new TreeNode(1)), new TreeNode(5,null,new TreeNode(5)));
        TreeNode treeNode2 = new TreeNode(1,new TreeNode(4,new TreeNode(4),new TreeNode(4)), new TreeNode(5,null,new TreeNode(5)));
        //TreeNode treeNode3 = new TreeNode(1,new TreeNode(,new TreeNode(4),new TreeNode(4)), new TreeNode(5,null,new TreeNode(5)));
        int i = solution.longestUnivaluePath(treeNode2);
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
    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
    //    遍历每个节点
    //    计算 同值情况下 左子树长度 + 右子树长度
    //    进行最大值计算
        dfs(root,0);
        return max;
    }

    private void dfs(TreeNode root,int count) {
        //边界处理
        if(root==null){
            return;
        }
        //业务处理，比较max
        if(root.left!=null && root.val == root.left.val){
            count++;
        }
        if(root.right!=null && root.val == root.right.val){
            count++;
        }
        if(count>max){
            max = count;
        }
        //dfs遍历子节点
        dfs(root.left,count);
        dfs(root.right,count);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}