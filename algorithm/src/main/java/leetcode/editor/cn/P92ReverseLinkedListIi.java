//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 👍 1226 👎 0

package leetcode.editor.cn;
//Java：反转链表 II
public class P92ReverseLinkedListIi{
    public static void main(String[] args) {

        Solution solution = new P92ReverseLinkedListIi().new Solution();
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //建立哑节点，返回的时候，返回dummyNode.next就行了。 还是很方便的
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        //声明pre前驱节点
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        //确定left节点
        ListNode leftNode = pre.next;

        //确定right节点
        ListNode rightNode = head;
        for (int i = 0; i < right - 1; i++) {
            rightNode = rightNode.next;
        }

        //确定后继节点
        ListNode succ = rightNode.next;

        //切断链表左侧
        pre.next = null;
        //切断链表右侧
        rightNode.next = null;

        //进行单个链表的反转
        reverseListNode(leftNode);

        //前驱节点指向反转后的右节点
        pre.next = rightNode;
        //反转后的左接地指向后继节点
        leftNode.next = succ;
        return dummyNode.next;
    }

    private void reverseListNode(ListNode leftNode) {
        // cur指向当前节点
        ListNode cur = leftNode;
        // pre为前驱节点
        ListNode pre = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
