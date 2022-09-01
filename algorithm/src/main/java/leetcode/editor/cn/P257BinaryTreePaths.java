//给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。 
//
// 叶子节点 是指没有子节点的节点。 
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,5]
//输出：["1->2->5","1->3"]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：["1"]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 100] 内 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 深度优先搜索 字符串 回溯 二叉树 👍 743 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

//Java：二叉树的所有路径
public class P257BinaryTreePaths{
    public static void main(String[] args) {
        Solution solution = new P257BinaryTreePaths().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        calPath(root,paths,"");
        return  paths;
    }

    public void calPath(TreeNode root, List<String> pathList,String path){
        if(root !=null){
            StringBuffer pathSB = new StringBuffer(path);
            //加入当前路径
            pathSB.append(root.val);
            //叶子结点
            if(root.left ==null && root.right == null){
                pathList.add(pathSB.toString());
            } else{
                pathSB.append("->");
                calPath(root.left, pathList, pathSB.toString());
                calPath(root.right, pathList, pathSB.toString());
            }

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
