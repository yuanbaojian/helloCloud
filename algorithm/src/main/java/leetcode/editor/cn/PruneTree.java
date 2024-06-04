package leetcode.editor.cn;

public class PruneTree {


    /*
    给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。

节点 node 的子树为 node 本身，以及所有 node 的后代。



示例 1:

输入: [1,null,0,0,1]
输出: [1,null,0,null,1]
解释:
只有红色节点满足条件“所有不包含 1 的子树”。
右图为返回的答案。
     */

    public TreeNode pruneTree(TreeNode root) {
        // 1. 递归遍历二叉树
        if(root == null){
            return null;
        }
        if (judgeZero(root)){
            return null;
        }

        if (judgeZero(root.left)){
            root.left =null;
        } else{
            root.left = pruneTree(root.left);
        }

        if (judgeZero(root.right)){
            root.right =null;
        } else{
            root.right = pruneTree(root.right);
        }
        return root;
    }

    public TreeNode pruneTree2(TreeNode root) {
        // 终止条件
        if(root == null){
            return null;
        }

        // 递归处理
        root.left = pruneTree2(root.left);
        root.right = pruneTree2(root.right);

        // 当前特殊处理,后续遍历
        if (root.val == 0 && root.left == null && root.right ==null){
            return null;
        }
        return root;
    }

    boolean judgeZero(TreeNode root){
        if(root == null){
            return true;
        }
        // 单个 0
        if (root.val == 0 && root.left==null && root.right ==null){
            return true;
        }
        // 非0
        if (root.val == 1){
            return false;
        }
        // 为0，但有子树不为0
        if (root.val ==0){
            return judgeZero(root.left) == true &&judgeZero(root.right)==true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);

        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(1);
        node1.right = node2;
        node2.left = node3;
        node2.right = node4;
        PruneTree pruneTree = new PruneTree();
        TreeNode treeNode = pruneTree.pruneTree2(node1);
        System.out.println("treeNode = " + treeNode);
    }
}
