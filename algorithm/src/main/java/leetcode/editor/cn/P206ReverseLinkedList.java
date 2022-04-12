////给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
//// 
//// 
//// 
////
//// 示例 1： 
////
//// 
////输入：head = [1,2,3,4,5]
////输出：[5,4,3,2,1]
//// 
////
//// 示例 2： 
////
//// 
////输入：head = [1,2]
////输出：[2,1]
//// 
////
//// 示例 3： 
////
//// 
////输入：head = []
////输出：[]
//// 
////
//// 
////
//// 提示： 
////
//// 
//// 链表中节点的数目范围是 [0, 5000] 
//// -5000 <= Node.val <= 5000 
//// 
////
//// 
////
//// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
//// 
//// 
//// Related Topics 递归 链表 👍 2363 👎 0
//

package leetcode.editor.cn;
//Java：反转链表
public class P206ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,null)));
        ListNode listNode1 = solution.reverseList(listNode);
        System.out.println("listNode1 = " + listNode1);
        // TO TEST
    }



class Solution {
    public ListNode reverseList(ListNode head) {
       ListNode current = head;
       ListNode pre =null;
       while (current!=null){
           ListNode nextNode = current.next;
           current.next = pre;
           pre = current;
           current = nextNode;
       }
       return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
