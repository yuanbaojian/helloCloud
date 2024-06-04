package leetcode.editor.cn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TreeInfectionAmountOfTime {
    Queue<TreeNode> queue = new LinkedList<>();

    Set<TreeNode> infectedSet = new HashSet<>();

    int maxTime = 0;

    public static void main(String[] args) {

    }

    // 广度遍历
    public int amountOfTime(TreeNode root, int start) {
        // 1.定位start节点
        TreeNode startNode = findNode(root, 3);

        //2.进行广度遍历
        queue.add(startNode);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (startNode.left != null && !infectedSet.contains(startNode.left) ){
                    infectedSet.add(startNode.left);
                    queue.add(startNode.left);
                }

                if (startNode.right != null && !infectedSet.contains(startNode.right) ){
                    infectedSet.add(startNode.right);
                    queue.add(startNode.right);

                }
                maxTime++;
            }
        }
        return maxTime-1;
    }

    // 深度优先搜索构建图



    static TreeNode findNode(TreeNode treeNode, int start){
        if (treeNode==null){
            return null;
        }
        if(treeNode.val == start){
            return treeNode;
        }
        TreeNode tempLeft = findNode(treeNode.left, start);
        if(tempLeft.val == start){
            return tempLeft;
        }
        TreeNode tempRight = findNode(treeNode.right, start);
        if(tempRight.val == start){
            return tempRight;
        }
        return null;
    }
}
