package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

/** * @author  daijiyong * @date
2022-10-11 11:39:35 */
public class Q1790CheckIfOneStringSwapCanMakeStringsEqual{    //给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
//
// 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入：s1 = "bank", s2 = "kanb"
//输出：true
//解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
// 
//
// 示例 2： 
//
// 输入：s1 = "attack", s2 = "defend"
//输出：false
//解释：一次字符串交换无法使两个字符串相等
// 
//
// 示例 3： 
//
// 输入：s1 = "kelb", s2 = "kelb"
//输出：true
//解释：两个字符串已经相等，所以不需要进行字符串交换
// 
//
// 示例 4： 
//
// 输入：s1 = "abcd", s2 = "dcba"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 100 
// s1.length == s2.length 
// s1 和 s2 仅由小写英文字母组成 
// 
//
// Related Topics 哈希表 字符串 计数 👍 60 👎 0
    public static void main(String[] args) {
        Solution solution = new Q1790CheckIfOneStringSwapCanMakeStringsEqual().new Solution();
        String s1 = "bank";
        String s2 = "kanb";
        boolean b = solution.areAlmostEqualV2(s1, s2);
        System.out.println("b = " + b);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        boolean result = true;
        //不同的个数
        int unEqualCount = 0;
        int unEqualIndex = 0;
        int [] s1UnEqual = new int[2];
        int [] s2UnEqual = new int[2];
        //两个指针，一起动
        int i = 0 , j = 0;
        while (i<s1.length()){
            //不等，需要记录下
            if(s1.charAt(i) != s2.charAt(j)){
                //如超过1， 直接返回false
                if(unEqualIndex>1){
                    unEqualCount++;
                    result = false;
                    break;
                }
                s1UnEqual[unEqualIndex] = s1.charAt(i);
                s2UnEqual[unEqualIndex] = s2.charAt(j);
                unEqualIndex++;
                //不相等的计数器加一
                unEqualCount++;
                //如超过2， 直接返回false

                if(unEqualCount>2){
                    result = false;
                    break;
                }
            }
            i++;
            j++;
        }
        //刚好换两次
        if(unEqualCount == 2 ){
            if(s1UnEqual[0] == s2UnEqual[1] && s1UnEqual[1] == s2UnEqual[0]){
                result = true;
            } else {
                result = false;
            }
        } else if (unEqualCount ==1 ) {
            result = false;
        } else if (unEqualCount == 0) {
            result = true;
        }

        return result;
    }

    /**
     * 使用下标法
     * @param s1 字符串1
     * @param s2 字符串2
     * @return
     */
    public boolean areAlmostEqualV2(String s1, String s2) {

        //s1 = "bank",
        //s2 = "kanb"
        boolean result = true;
        //不同的个数
        List<Integer> diffIndex = new LinkedList<>();

        for (int i = 0; i < s1.length(); i++) {
            //记录不同的下标位置
            if(s1.charAt(i) != s2.charAt(i)){
                diffIndex.add(i);
                //超过两个直接退出
                if(diffIndex.size() > 2){
                    result = false;
                    break;
                }
            }
        }
        if(diffIndex.size() == 2){
            if(s1.charAt(diffIndex.get(0))  == s2.charAt(diffIndex.get(1))  &&
                    s1.charAt(diffIndex.get(1))  == s2.charAt(diffIndex.get(0))){
                result = true;
            } else {
                result = false;
            }
        } else if (diffIndex.size() == 1) {
            result = false;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}