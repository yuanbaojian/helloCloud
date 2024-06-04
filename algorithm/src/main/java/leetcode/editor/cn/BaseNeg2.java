package leetcode.editor.cn;

public class BaseNeg2 {


    /*
    给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。

注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。



示例 1：

输入：n = 2
输出："110"
解释：(-2)2 + (-2)1 = 2


示例 2：

输入：n = 3
输出："111"
解释：(-2)2 + (-2)1 + (-2)0 = 3


示例 3：

输入：n = 4
输出："100"
解释：(-2)2 = 4

注意： 不是二进制，二十负二进制。 上述的因子都是 -2  ， 不是2
     */
    public String baseNeg2(int n) {
        // 异常处理
        if (n==0){
            return "0";
        }
        StringBuilder result = new StringBuilder();
        // 关键代码
        while (n != 0) {
            int remainder =n % -2;
            n = n / -2;

            if ( remainder < 0){
                remainder+=2;
                n+=1;
            }
            result.append(remainder);
        }


        // 2. 计算
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        BaseNeg2 baseNeg2 = new BaseNeg2();
        String result = baseNeg2.baseNeg2(2);
        System.out.println("result = " + result);
    }

}
