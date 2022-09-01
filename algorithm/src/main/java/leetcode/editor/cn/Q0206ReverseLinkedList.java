package leetcode.editor.cn;

import java.util.ArrayList;

/** * @author  yuanbaojian * @date    2022-06-21 11:36:30 */
public class Q0206ReverseLinkedList{    //给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 👍 2577 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0206ReverseLinkedList().new Solution();
        ListNode listNode = new ListNode(1, new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        ListNode listNode2 = new ListNode();
        ListNode result = solution.reverseList(listNode2);
        System.out.println("listNode1 = " + result);
    }    //leetcode submit region begin(Prohibit modification and deletion)
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
    public ListNode reverseList(ListNode head) {
        //递归终止条件
        if(head == null || head.next == null){
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
}