package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 树的序列化&反序列化
public class TreeSerialize {


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(10);
        TreeNode leftNode = new TreeNode(8);
        TreeNode rightNode = new TreeNode(15);
        treeNode.left =leftNode;
        treeNode.right = rightNode;

        leftNode.left = new TreeNode(1);
        leftNode.right = new TreeNode(2);

        rightNode.left = new TreeNode(3);

        String ser = serialize(treeNode);
        System.out.println("ser = " + ser);

        TreeNode deSer = deserialize(ser);
        System.out.println("deSer = " + deSer);
    }



    public static String serialize(TreeNode root){
        StringBuilder builder = new StringBuilder();
        serializeUtil(root,builder);
        return builder.toString();
    }

    // string 传入进去后，修改的不是引用，而是一个新的值
    public static void testString(String content){
        content += "test";
    }

    public static void serializeUtil(TreeNode root, StringBuilder builder){
        if (root == null ){
            builder.append("#").append(" ");
            return;
        }
        builder.append(root.val).append(" ");
        serializeUtil(root.left,builder);
        serializeUtil(root.right,builder);

    }

    public static TreeNode deserialize(String path){
        String[] array = path.split(" ");
        List<String> list = new LinkedList<>(Arrays.asList(array)) ;
        return DeserializeUtil(list);
    }


    public static TreeNode DeserializeUtil(List<String> items){
       if (items.size() == 0){
           return null;
       }
       String current = items.get(0);
       items.remove(0);
        if ("#".equals(current)){
           return null;
       }
       TreeNode root = new TreeNode(Integer.valueOf(current));
       root.left = DeserializeUtil(items);
       root.right = DeserializeUtil(items);
       // 需要迭代完所有
       return root;
    }



}
