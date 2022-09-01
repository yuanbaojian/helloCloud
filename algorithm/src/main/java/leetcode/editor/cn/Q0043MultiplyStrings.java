package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/** * @author  yuanbaojian * @date    2022-08-30 14:04:19 */
public class Q0043MultiplyStrings{    //给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。 
//
// 
//
// 示例 1: 
//
// 
//输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 
//输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 200 
// num1 和 num2 只能由数字组成。 
// num1 和 num2 都不包含任何前导零，除了数字0本身。 
// 
// Related Topics 数学 字符串 模拟 👍 1039 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0043MultiplyStrings().new Solution();
        String multiply = solution.multiply("123", "123");
        System.out.println("multiply = " + multiply);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String multiply(String num1, String num2) {
        //11 * 12
        //   22
        //  110

        // 123 * 123
        //     369
        //    2460
        //   12300

        //12 * 123
        //   36
        //  240
        // 1200
        char[] number1 = num1.toCharArray();
        char[] number2 = num2.toCharArray();

        Stack<String> valueString = new Stack<>();
        for (int i = number1.length-1; i >= 0; i--) {
            Stack<String> tempValueString = new Stack<>();
            String tempValue = "";
            //高位填充0
            for (int j = 0; j < number1.length - 1-i; j++) {
                tempValue= "0"+tempValue;
                tempValueString.add("0");
            }
            for (int j = number2.length-1; j >= 0 ; j--) {
                //tempValueString.add()
                tempValue = Integer.parseInt(String.valueOf(number1[i])) * Integer.parseInt(String.valueOf(number2[j]))  + tempValue;
            }
            tempValueString.add(tempValue);
        }
        //拿到最长字符串
        String lastNumber = valueString.peek();
        char[] lastNumberArray = lastNumber.toCharArray();
        List<Integer> result = new LinkedList<>();
        int addOne = 0;
        //按照最长字符串的标准进行各个位上的数字相加
        for (int i = 0; i < lastNumberArray.length; i++) {
            int tempValue = 0;
            for (int j = 0; j < valueString.size(); j++) {
                String item = valueString.get(j);
                char[] charArray = item.toCharArray();
                if(i<charArray.length){
                    tempValue += Integer.parseInt(String.valueOf(charArray[i]));
                }
            }
            //前置进位
            if(addOne> 0){
                tempValue += addOne;
            }
            //大于9的判断，需要取余进位
            if(tempValue>9){
                addOne = tempValue / 10;
                tempValue = tempValue % 10;
            } else {
                addOne = 0;
            }
            result.add(tempValue);
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}