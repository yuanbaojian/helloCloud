package leetcode.editor.cn;

import java.util.*;

/** * @author  yuanbaojian * @date    2022-09-05 08:49:19 */
public class Q0652FindDuplicateSubtrees{    //给定一棵二叉树 root，返回所有重复的子树。
//
// 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。 
//
// 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,null,2,4,null,null,4]
//输出：[[2,4],[4]] 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [2,1,1]
//输出：[[1]] 
//
// 示例 3： 
//
// 
//
// 
//输入：root = [2,2,2,3,null,3,null]
//输出：[[2,3],[3]] 
//
// 
//
// 提示： 
//
// 
// 树中的结点数在[1,10^4]范围内。 
// -200 <= Node.val <= 200 
// 
// Related Topics 树 深度优先搜索 哈希表 二叉树 👍 491 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0652FindDuplicateSubtrees().new Solution();
        TreeNode treeNode =
                new TreeNode(1,
                        new TreeNode(2,new TreeNode(4),null),
                        new TreeNode(3,new TreeNode(2,new TreeNode(4),null),new TreeNode(4)));
        List<TreeNode> duplicateSubtrees = solution.findDuplicateSubtrees(treeNode);
        System.out.println("duplicateSubtrees = " + duplicateSubtrees);
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


    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
         //找到重复的树， 有重复就可以使用HashSet进行重复判断了
        //1. 遍历塞入当前head节点   到 hashset中
        //2. 塞入前判断hashSet中是否包含该root  (TreeNode没有重写equals方法，所以没办法直接使用Set进行重复判断)
        //3. 记录重复数据，最终返回


        TreeNode head = root;
        //深度遍历
        String currentString = dfsByString(head);
        //规律  root(left)(right), 递归下去
        //我的  1 (2 (  4()()  ) () )   (3(2(4()())())(4()()))

        //题解  1(2(4()())())(3(2(4()())())(4()()))
        return new ArrayList<TreeNode> (treeNodeResult);
    }

    //临时变量
    Map<String,TreeNode> seen = new HashMap<>();

    Set<TreeNode> treeNodeResult = new HashSet<>();

    /**
     * 使用字符串进行 重复判断
     * @param head
     */
    public String dfsByString(TreeNode head){
        //递归终止条件
        if(head==null){
            return "";
        }

        //计算出当前的序列化值
        String tempValue = String.valueOf(head.val);
        tempValue = tempValue + "(" + dfsByString(head.left) + ")";
        tempValue = tempValue + "(" + dfsByString(head.right) + ")";

        //第一次放到hash表中， 不重复
        if(!seen.containsKey(tempValue)){
            seen.put(tempValue,head);
        } else {
            //重复数据，放到结果集中
            treeNodeResult.add(seen.get(tempValue));
        }
        //返回当前的序列化
        return tempValue;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}