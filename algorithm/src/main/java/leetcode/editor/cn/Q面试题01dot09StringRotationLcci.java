package leetcode.editor.cn;
/** * @author  daijiyong * @date    
2022-09-29 16:54:38 */
public class Q面试题01dot09StringRotationLcci{    //字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
//
// 示例1: 
//
//  输入：s1 = "waterbottle", s2 = "erbottlewat"
// 输出：True
// 
//
// 示例2: 
//
//  输入：s1 = "aa", s2 = "aba"
// 输出：False
// 
//
// 
// 
//
// 提示： 
//
// 
// 字符串长度在[0, 100000]范围内。 
// 
//
// 说明: 
//
// 
// 你能只调用一次检查子串的方法吗？ 
// 
//
// Related Topics 字符串 字符串匹配 👍 190 👎 0
    public static void main(String[] args) {
        Solution solution = new Q面试题01dot09StringRotationLcci().new Solution();
        boolean flipedString = solution.isFlipedString("waterbottl1e", "erbottlewat");
        System.out.println("flipedString = " + flipedString);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        //长度比较
        if(s1.length() != s2.length()){
            return false;
        }
        //使用组合字符串技巧
        return (s2+s2).contains(s1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}