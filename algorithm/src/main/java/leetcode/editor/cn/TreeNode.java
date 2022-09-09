package leetcode.editor.cn;

import lombok.Data;

@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public boolean equals(TreeNode obj) {

        return obj.val == this.val;
    }
}
