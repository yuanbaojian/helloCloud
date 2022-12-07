package leetcode.editor.cn;
/** * @author  daijiyong * @date    
2022-11-13 19:00:19 */
public class Q0029DivideTwoIntegers{    //给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2³¹, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
//
// Related Topics 位运算 数学 👍 1013 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0029DivideTwoIntegers().new Solution();
        int divide = solution.divide(-2147483648
                , 1);
        System.out.println("divide = " + divide);

    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int divide(int dividend, int divisor) {
        //有个有意思的做法，用减法看看被除数里到底有几个除数哈哈
        //符号位处理
        boolean dividendZero = dividend > 0;
        boolean divisorZero = divisor > 0;

        long dividendLong = Math.abs((long)dividend);
        long divisorLong = Math.abs((long)divisor);
        long count = 0;
        //开始减
        while (dividendLong>=divisorLong){
            //减去一个
            dividendLong -= divisorLong;
            count++;
        }
        //需要变成负数
        if( (dividendZero && !divisorZero) || (!dividendZero && divisorZero)){
            count*=-1;
        }
        //溢出处理
        if(count>Integer.MAX_VALUE || count<Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}