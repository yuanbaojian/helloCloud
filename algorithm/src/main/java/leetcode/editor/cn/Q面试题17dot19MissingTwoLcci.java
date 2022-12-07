package leetcode.editor.cn;
/** * @author  daijiyong * @date    
2022-09-26 08:31:24 */
public class Q面试题17dot19MissingTwoLcci{    //给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
//
// 以任意顺序返回这两个数字均可。 
//
// 示例 1: 
//
// 输入: [1]
//输出: [2,3] 
//
// 示例 2: 
//
// 输入: [2,3]
//输出: [1,4] 
//
// 提示： 
//
// 
// nums.length <= 30000 
// 
//
// Related Topics 位运算 数组 哈希表 👍 92 👎 0
    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        int result = a^b;
        System.out.println("result = " +result);
        Solution solution = new Q面试题17dot19MissingTwoLcci().new Solution();
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int[] missingTwo(int[] nums) {
        // 难度在于
        //1. 控制时间复杂度为O(N), 只能for循环遍历数组
        //2. 控制空间复杂度为O(1)， 不能再开辟一个数组对象

        // 对于位运算的使用，我还需要加深理解
        int sum = 0;
        //不全缺失的两个数
        int n = nums.length+2;
        for (int i = 0; i < nums.length; i++) {
            //这个是啥来着， 异或，相同取0，不同取1
            sum ^= nums[i];
        }
        //防溢出？？？
        int lsb = (sum == Integer.MAX_VALUE ? sum:sum&(-sum));
        return null;

    }


}
//leetcode submit region end(Prohibit modification and deletion)
}