package leetcode.editor.cn;
/** * @author  daijiyong * @date    2022-09-21 00:14:48 */
public class Q0854KSimilarStrings{    //对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
//
// 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab", s2 = "ba"
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：s1 = "abc", s2 = "bca"
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length <= 20 
// s2.length == s1.length 
// s1 和 s2 只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母 
// s2 是 s1 的一个字母异位词 
// 
//
// Related Topics 广度优先搜索 字符串 👍 132 👎 0
    public static void main(String[] args) {        Solution solution = new Q0854KSimilarStrings().new Solution();    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int kSimilarity(String s1, String s2) {
        return 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}