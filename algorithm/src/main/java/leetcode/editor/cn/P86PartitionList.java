//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。 
//
// 你应当 保留 两个分区中每个节点的初始相对位置。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,4,3,2,5,2], x = 3
//输出：[1,2,2,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [2,1], x = 2
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 200] 内 
// -100 <= Node.val <= 100 
// -200 <= x <= 200 
// 
// Related Topics 链表 双指针 👍 545 👎 0

package leetcode.editor.cn;
//Java：分隔链表
public class P86PartitionList{
    public static void main(String[] args) {
        Solution solution = new P86PartitionList().new Solution();
        ListNode listNode = new ListNode(1,new ListNode(4,new ListNode(3,new ListNode(2, new ListNode(5, new ListNode(2))))));
        ListNode listNode2 = new ListNode(2,new ListNode(1,null));
        solution.partition(listNode2,2);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */



class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode tempHead = head;
        ListNode smaller = null;
        ListNode larger = null;
        while (tempHead!=null){
            ListNode listNode = new ListNode();
            listNode.val = tempHead.val;
            if(tempHead.val<x){
                //为空，则直接赋值
                if(smaller==null){
                    smaller = listNode;
                } else {
                    //尾部加入新数据
                    ListNode temp = smaller;
                    while(temp.next != null){
                        temp = temp.next;
                    }
                    temp.next = listNode;
                }
            } else{
                if(larger == null){
                    larger = listNode;
                } else{
                    //尾部加入新数据
                    ListNode temp = larger;
                    while(temp.next != null){
                        temp = temp.next;
                    }
                    //由于是larger的引用，更改temp即是更改larger
                    temp.next = listNode;
                }
            }
            tempHead = tempHead.next;
        }
        //链表需要一个临时变量，用于遍历 + 赋值
        if(smaller == null){
            return larger;
        }
        ListNode temp = smaller;
        while(temp.next !=null){
            temp = temp.next;
        }
        temp.next = larger;
        return smaller;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
