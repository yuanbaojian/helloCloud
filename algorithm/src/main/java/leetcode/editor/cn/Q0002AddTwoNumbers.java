package leetcode.editor.cn;
/** * @author  daijiyong * @date    
2022-11-10 17:29:08 */public class Q0002AddTwoNumbers{    //给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
// 
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
//
// Related Topics 递归 链表 数学 👍 8881 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0002AddTwoNumbers().new Solution();
        ListNode listNode1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode listNode2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        //l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        ListNode listNode3 = new ListNode(9, new ListNode(9, new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9)))))));
        ListNode listNode4 = new ListNode(9, new ListNode(9, new ListNode(9,new ListNode(9))));
        ListNode listNode = solution.addTwoNumbers(listNode3, listNode4);
        System.out.println("listNode = " + listNode.toString());
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //哑节点作为辅助
        ListNode head = null,tail = null;
        int add = 0;
        //先遍历l1和l2, 直到全部遍历完成
        while (l1!=null || l2!=null){
            //得到当前值
            int l1Value = l1 == null ? 0 :l1.val;
            int l2Value = l2 == null ? 0 :l2.val;
            int tempValue = l1Value + l2Value + add;
            //拿商当值
            int value = tempValue % 10;
            //用于进位
            add = tempValue / 10;
            ListNode currentNode = new ListNode(value);
            //处理第一个节点
            if(head == null){
                tail = currentNode;
                //讲
                head = tail;
            } else{
                tail.next = currentNode;
                //tail保证在最后面
                tail = tail.next;
            }

            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
        }
        //最后的进位判断
        if(add>0){
            tail.next = new ListNode(add);
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}