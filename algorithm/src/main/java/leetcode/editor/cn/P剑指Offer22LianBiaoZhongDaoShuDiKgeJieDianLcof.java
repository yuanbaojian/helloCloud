//输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。 
//
// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。 
//
// 
//
// 示例： 
//
// 
//给定一个链表: 1->2->3->4->5, 和 k = 2.
//
//返回链表 4->5. 
// Related Topics 链表 双指针 👍 352 👎 0

package leetcode.editor.cn;

//Java：链表中倒数第k个节点
public class P剑指Offer22LianBiaoZhongDaoShuDiKgeJieDianLcof{
    public static void main(String[] args) {
        Solution solution = new P剑指Offer22LianBiaoZhongDaoShuDiKgeJieDianLcof().new Solution();
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3, new ListNode(4, new ListNode(5)))));
        final ListNode kthFromEnd = solution.getKthFromEnd(listNode, 1);
        System.out.println("kthFromEnd = " + kthFromEnd.val);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        //思路：双指针， 两个指针拉开k个身位
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        //这时候已经有两个指针已经有两个身位了， 可以遍历了
        while (p1!=null){
            p2 = p2.next;
            p1 = p1.next;
        }
        return p2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
