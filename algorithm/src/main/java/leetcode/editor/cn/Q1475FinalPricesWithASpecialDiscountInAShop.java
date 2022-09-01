package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/** * @author  yuanbaojian * @date    2022-09-01 08:52:25 */
public class Q1475FinalPricesWithASpecialDiscountInAShop{    //给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
//
// 商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，其中 j 是满足 j > i 且 prices[j] 
//<= prices[i] 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。 
//
// 请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。 
//
// 
//
// 示例 1： 
//
// 输入：prices = [8,4,6,2,3]
//输出：[4,2,4,2,3]
//解释：
//商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
//商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
//商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
//商品 3 和 4 都没有折扣。
// 
//
// 示例 2： 
//
// 输入：prices = [1,2,3,4,5]
//输出：[1,2,3,4,5]
//解释：在这个例子中，所有商品都没有折扣。
// 
//
// 示例 3： 
//
// 输入：prices = [10,1,1,6]
//输出：[9,0,1,6]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 500 
// 1 <= prices[i] <= 10^3 
// 
// Related Topics 栈 数组 单调栈 👍 97 👎 0
    public static void main(String[] args) {
        Solution solution = new Q1475FinalPricesWithASpecialDiscountInAShop().new Solution();
        int[] array = {10,1,1,6};
        int[] finalPrices = solution.finalPricesV2(array);
        System.out.println("finalPrices = " + finalPrices);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] finalPrices(int[] prices) {
        //向后找到第一个 <= 的数
        //对应下标计算 差值
        // 返回结果

        int[] result = new int[prices.length];
        //方法1：遍历查找， 时间复杂度O(N2)  空间复杂度O(N)
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                //能找到
                if(prices[j]<= prices[i]){
                    result[i] = prices[i]-prices[j];
                    break;
                }
                //到达最后一个也没有<=的
                if(j==prices.length-1){
                    result[i] = prices[i];
                }
            }
        }
        //处理最后一个元素,肯定是本身
        result[prices.length-1] = prices[prices.length-1];
        return result;
    }

    public int[] finalPricesV2(int[] prices) {
        //向后找到第一个 <= 的数
        //对应下标计算 差值
        // 返回结果

        //可以考虑用栈来做
        //8,4,6,2,3
        //4,2,4,2,3

        //price 8
        //下个数 4
        //result
        //栈顶下标 0

        //price 4
        //下个数 6
        //result 4,
        //栈顶下标 1


        //price 4,6
        //下个数2
        //result 4
        //栈顶下标 2

        //price 4,
        //下个数2
        //result 4, , 4
        //下标 1

        //price 2
        //下个数3
        //result 4, 2, 4
        //下标 4

        //price 2,3
        //下个数 无
        //result 4, 2, 4
        //下标 5

        //index i-1
        int[] result = new int[prices.length];
        Deque<Integer> deque = new ArrayDeque<>();

        //往队列中丢数据
        for (int i = 0; i < prices.length; i++) {
            //空栈直接插入
            if(deque.isEmpty()){
                deque.add(prices[i]);
            } else {
                //待插入元素小于栈顶，pop，并计算差值
                while (!deque.isEmpty() &&deque.peek() > prices[i]){
                //    如何确定结果下标？
                    result[i-1] = deque.peek() - prices[i];
                    deque.pop();
                }
            }
        }
        //对于剩余的栈内数据，需要取其本身，再确定下标
        return result;
    }

    public int[] finalPricesV3(int[] prices) {
        //定义返回结果集
        int[] result = new int[prices.length];
        int n = prices.length;
        Deque<Integer> stack = new ArrayDeque<>();
        //倒序配置
        //8,4,6,2,3
        //4,2,4,2,3
        for (int i = n-1; i > 0; i--) {
            while (!stack.isEmpty() && stack.peek()> prices[i]){
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = prices[i];
            } else{
                result[i] = prices[i] - stack.peek();
            }
            stack.push(prices[i]);
        }
        //对于剩余的栈内数据，需要取其本身，再确定下标
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}