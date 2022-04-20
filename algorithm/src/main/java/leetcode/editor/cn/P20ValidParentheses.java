//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
// 示例 4：
//
//
//输入：s = "([)]"
//输出：false
//
//
// 示例 5：
//
//
//输入：s = "{[]}"
//输出：true
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10⁴
// s 仅由括号 '()[]{}' 组成
//
// Related Topics 栈 字符串 👍 3190 👎 0

package leetcode.editor.cn;

import java.util.Stack;

//Java：有效的括号
public class P20ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        final boolean valid = solution.isValid2("()");
        System.out.println("valid = " + valid);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid2(String s) {
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                // 很巧妙的用右括号代替
                if( c == '('){
                    stack.push(')');
                } else if( c =='['){
                    stack.push(']');
                } else if( c =='{'){
                    stack.push('}');
                } else if(stack.isEmpty() || c!=stack.pop()){
                    return false;
                }
            }
            return stack.isEmpty();
        }

        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char current = charArray[i];
                if (i > 0 && stack.size() >0) {
                    Character stackTop = stack.peek();
                    if ('(' == stackTop && ')' == current) {
                        stack.pop();
                        continue;
                    }
                    if ('[' == stackTop  && ']' == current ) {
                        stack.pop();
                        continue;

                    }
                    if ('{' == stackTop && '}' == current) {
                        stack.pop();
                        continue;
                    }

                }
                stack.push(current);
            }
            return stack.isEmpty();
        }

//leetcode submit region end(Prohibit modification and deletion)

    }
}