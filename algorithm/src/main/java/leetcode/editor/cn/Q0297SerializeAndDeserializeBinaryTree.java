package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/** * @author  yuanbaojian * @date    2022-09-05 16:02:17 */
public class Q0297SerializeAndDeserializeBinaryTree{    //序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
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
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 10⁴] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 968 👎 0
    public static void main(String[] args) {
        Codec solution = new Q0297SerializeAndDeserializeBinaryTree().new Codec();
        TreeNode treeNode =
                new TreeNode(1,
                        new TreeNode(2,new TreeNode(4),null),
                        new TreeNode(3,new TreeNode(2,new TreeNode(4),null),new TreeNode(4)));
        TreeNode treeNode2 =
                new TreeNode(1,
                        new TreeNode(2),
                        new TreeNode(3,new TreeNode(4),new TreeNode(5)));
        String serialize = solution.serialize(treeNode2);
        System.out.println("serialize = " + serialize);
    }    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {



    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return dfs(root);
    }

    String serializeResult = "";

    /**
     * 这里要层次遍历
     * @param root
     * @return
     */
    private String fs(TreeNode root) {
        //递归终止处理
        if(root == null){
            return "null";
        }
        String tempValue = "";
        //定义双端队列，用于广度遍历
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        //用while进行广度遍历的推动
        while (!deque.isEmpty()){
            TreeNode top = deque.pop();
            serializeResult = serializeResult + top.val;
            if(top.left!=null){
                deque.addLast(top.left);
            }
            if(top.right!=null){
                deque.addLast(top.right);
            }
        }
        //组装当前序列化
        serializeResult = serializeResult + root.val;
        //递归处理子树
        serializeResult = serializeResult + "," + dfs(root.left);
        tempValue = tempValue + "," + dfs(root.right);
        //返回当前子树序列化
        return tempValue;
    }



    private String dfs(TreeNode root) {
        //递归终止处理
        if(root == null){
            return "null";
        }
        String tempValue = "";

        //组装当前序列化
        tempValue = tempValue + root.val;
        //递归处理子树
        tempValue = tempValue + "," + dfs(root.left);
        tempValue = tempValue + "," + dfs(root.right);
        //返回当前子树序列化
        return tempValue;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
}