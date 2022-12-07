package leetcode.editor.cn;
/** * @author  daijiyong * @date    
2022-09-25 18:33:12 */
public class Q0788RotatedDigits{    //我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
//
// 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况
//下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。 
//
// 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？ 
//
// 
//
// 示例： 
//
// 输入: 10
//输出: 4
//解释: 
//在[1, 10]中有四个好数： 2, 5, 6, 9。
//注意 1 和 10 不是好数, 因为他们在旋转之后不变。
// 
//
// 
//
// 提示： 
//
// 
// N 的取值范围是 [1, 10000]。 
// 
//
// Related Topics 数学 动态规划 👍 160 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0788RotatedDigits().new Solution();
        int i = solution.rotatedDigits(20);
        System.out.println("i = " + i);

    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rotatedDigits(int n) {
        //肯定要遍历每个数字
        int count = 0;
        for (int i = 1; i <= n; i++) {
            //拿到每个位上的数字
            int currentValue = i;
            boolean contain3or4or7 = false;
            boolean contain2or5or6or9 = false;
            //比较每一个数的结构？
            while (currentValue!=0){
                int temp = currentValue%10;
                currentValue = currentValue/10;
                //一共可能是 0 1 2 3 4 5 6 7 8 9
                // 不能有         3 4     7
                // 无要求   0 1             8
                // 需要有任意一个 2    5 6     9
                if(temp== 3 || temp== 4 || temp==7){
                    contain3or4or7 = true;
                    break;
                }
                if(temp== 2 || temp ==5 || temp==6 || temp ==9){
                    contain2or5or6or9 = true;
                }
            }
            if(contain3or4or7){
                continue;
            }
            if(contain2or5or6or9){
                count++;
                System.out.println("i = " + i);
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}