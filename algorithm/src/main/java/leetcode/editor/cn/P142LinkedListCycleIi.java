//给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。 
//
// 不允许修改 链表。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
//
// 
//
// 进阶：你是否可以使用 O(1) 空间解决此题？ 
// Related Topics 哈希表 链表 双指针 👍 1493 👎 0

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：环形链表 II
public class P142LinkedListCycleIi{
    public static void main(String[] args) {
        Solution solution = new P142LinkedListCycleIi().new Solution();
        // ListNode listNode1 = new ListNode(1);
        // ListNode listNode2 = new ListNode(2);
        // listNode2.next = listNode1;
        // listNode1.next = listNode2;
        // final ListNode listNode = solution.detectCycle(listNode1);
        // System.out.println("listNode = " + listNode);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode2;
        final ListNode listNode = solution.detectCycle(listNode1);
        System.out.println(" = " );
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {


    public ListNode detectCycle(ListNode head) {
        //快2慢1
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next !=null){
            slow = slow.next;
            fast =fast.next;
            if(fast.next!=null){
                fast = fast.next;
            }
            //快 = 慢，说明成环了
            if(slow == fast){
                return fast;
            }
        }
        //说明无环
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> listNodeSet= new HashSet<>();
        while (head!=null){
            if(listNodeSet.contains(head)){
                return head;
            }
            listNodeSet.add(head);
            head = head.next;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
