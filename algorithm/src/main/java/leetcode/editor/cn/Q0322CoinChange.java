package leetcode.editor.cn;
/** * @author  yuanbaojian * @date    2022-06-20 18:22:53 */
public class Q0322CoinChange{    //给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
// Related Topics 广度优先搜索 数组 动态规划 👍 1987 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0322CoinChange().new Solution();
        int[] coins = {1,2,5};
        int amount = 11;
        int dp = solution.coinChange(coins, amount);
        System.out.println("dp = " + dp);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int coinChange(int[] coins, int amount) {
        //初始化,默认都是0
        memo = new int[amount+1];
        return dp(coins,amount);
    }

    //需要使用备忘录，空间换时间
    int[] memo;

    private int dp(int[] coins, int amount) {
        //递归终止条件
        if(amount ==0){
            return 0;
        }

        if(amount < 0){
            return -1;
        }

        if(memo[amount] != 0){
            return memo[amount];
        }
        //需要是局部变量，每次都得用
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            //剩余数量
            int leftAmount = amount - coins[i];

            int leftCount = dp(coins, leftAmount);
            //表示此次组合无法组成amount,不参与最小值计算
            if(leftCount == -1){
                continue;
            }
            //取最小值，得在最后一次比较，先累加
            minCount = Math.min(minCount,leftCount+1);
        }
        memo[amount] =  minCount == Integer.MAX_VALUE ? -1:minCount;
        //为啥这样返回？？？
        return minCount == Integer.MAX_VALUE ? -1:minCount;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}