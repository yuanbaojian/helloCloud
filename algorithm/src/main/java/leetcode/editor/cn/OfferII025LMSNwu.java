package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-08-03 15:50:08 */

import java.util.Stack;

public class OfferII025LMSNwu{    //给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。 
//
// 
//
// 注意：本题与主站 445 题相同：https://leetcode-cn.com/problems/add-two-numbers-ii/ 
// Related Topics 栈 链表 数学 👍 59 👎 0
    public static void main(String[] args) {
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
        ListNode l1 = new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode l3 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode l4 = new ListNode(9, new ListNode(9, new ListNode(9)));

        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(5);

        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(9);

        ListNode l9 = new ListNode(1);
        ListNode l10 = new ListNode(9,new ListNode(9));
        Solution solution = new OfferII025LMSNwu().new Solution();
        ListNode listNode = solution.addTwoNumbers(l9, l10);
        System.out.println("listNode = " + listNode);
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

    /**
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode>  stack1 = new Stack<>();
        ListNode head ;
        ListNode head1 = l1 ;
        ListNode head2 = l2 ;
        while(l1!=null){
            stack1.push(l1);
            l1 = l1.next;
        }

        Stack<ListNode>  stack2 = new Stack<>();
        while(l2!=null){
            stack2.push(l2);
            l2 = l2.next;
        }

        Stack<ListNode> longStack;
        Stack<ListNode> shortStack;
        if(stack1.size() >= stack2.size()){
            longStack = stack1;
            shortStack = stack2;
            head = head1;
        } else {
            longStack = stack2;
            shortStack = stack1;
            head = head2;
        }
        Boolean addOne = false;
        while(!longStack.isEmpty()){
            ListNode longStackNode = longStack.pop();
            if(addOne){
                longStackNode.val+=1;
            }
            if(!shortStack.isEmpty()){
                ListNode shortStackNode = shortStack.pop();
                longStackNode.val = longStackNode.val + shortStackNode.val;
            }
            if(longStackNode.val >= 10){
                longStackNode.val-=10;
                addOne = true;
            } else {
                addOne = false;
            }
            //需要新增一位的特殊处理
            if(longStack.isEmpty() && addOne){
                return new ListNode(1,head);
            }
        }

        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}