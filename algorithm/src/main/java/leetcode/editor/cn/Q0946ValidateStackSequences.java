package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/** * @author  yuanbaojian * @date    2022-08-31 11:16:55 */
public class Q0946ValidateStackSequences{    //给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时
//，返回 true；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
// 
//
// 示例 2： 
//
// 
//输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= pushed.length <= 1000 
// 0 <= pushed[i] <= 1000 
// pushed 的所有元素 互不相同 
// popped.length == pushed.length 
// popped 是 pushed 的一个排列 
// 
// Related Topics 栈 数组 模拟 👍 298 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0946ValidateStackSequences().new Solution();
        int[] pushed = {1,2,3,4,5}, popped = {4,3,5,1,2};

        // 2 0
        // 2
        boolean b = solution.validateStackSequencesV2(pushed, popped);
        System.out.println("b = " + b);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
    //    空白栈，模拟进栈 & 出栈
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int i = 0; i < pushed.length; i++) {
            //模拟进栈
            stack.push(pushed[i]);
            //中途出栈检测
            if(!stack.isEmpty()){
                while (stack.peek()==popped[popIndex]){
                    stack.pop();
                    popIndex++;
                    if(stack.isEmpty()){
                        break;
                    }
                }
            }

        }
        //顺序出栈检测
        for (int i = popIndex; i < popped.length; i++) {
            if(stack.peek() == popped[popIndex]){
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    public boolean validateStackSequencesV2(int[] pushed, int[] popped) {
        //    空白栈，模拟进栈 & 出栈
        Stack<Integer> stack = new Stack<>();
        //1,2,3,4,5
        //5,4,3,2,1
        int popIndex = 0;
        for (int i = 0; i < pushed.length; i++) {
            //模拟进栈
            stack.push(pushed[i]);
            //出栈检测
            while (!stack.isEmpty() &&stack.peek()==popped[popIndex]){
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    public boolean validateStackSequencesV3(int[] pushed, int[] popped) {
        //  双端队列， 两端都可以进出
        Deque<Integer> deque = new ArrayDeque<>();
        //1,2,3,4,5
        //5,4,3,2,1
        int popIndex = 0;
        for (int i = 0; i < pushed.length; i++) {
            //模拟进队
            deque.offer(pushed[i]);
            //出栈检测
            while (!deque.isEmpty() &&deque.peek()==popped[popIndex]){
                deque.pop();
                popIndex++;
            }
        }
        return deque.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}