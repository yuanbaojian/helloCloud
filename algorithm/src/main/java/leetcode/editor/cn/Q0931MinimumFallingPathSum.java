package leetcode.editor.cn;

/** * @author  yuanbaojian * @date    2022-06-29 08:46:43 */
public class Q0931MinimumFallingPathSum{    //给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
//
// 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第
//一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1
//, col + 1) 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
//输出：13
//解释：如图所示，为和最小的两条下降路径
// 
//
// 示例 2： 
//
// 
//
// 
//输入：matrix = [[-19,57],[-40,-5]]
//输出：-59
//解释：如图所示，为和最小的下降路径
// 
//
// 
//
// 提示： 
//低门槛的不要做！！！
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 100 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 👍 181 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0931MinimumFallingPathSum().new Solution();
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        int[][] matrix1 = {{-19,57},{-40,-5}};
        int result = solution.minFallingPathSum(matrix);
        System.out.println("result = " + result);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        memo = new int[matrix.length][matrix.length];
        int result = Integer.MAX_VALUE;
        //矩阵宽
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            result = Math.min(result,dp(matrix,length-1,i));
        }
       return result;
    }

    int[][] memo;

    private int dp(int[][] matrix,int i ,int j) {
        //应该记录 matrix[0][.] 到 matrix[i][j]的最小路径和

        //非法参数校验
        if(i<0 || j<0 || j>= matrix.length || j>=matrix[0].length){
            //返回一个特殊大值
            return 9999;
        }

        //如果第一行， 直接返回
        if(i==0){
            return matrix[i][j];
        }
        if(memo[i][j] != 0){
            return memo[i][j];
        }

        //正常递归
        int minValue = getMin( dp(matrix,i-1,j-1), dp(matrix,i-1,j), dp(matrix,i-1,j+1));
        int result = matrix[i][j] + minValue;
        //返回当前层的值
        memo[i][j] = result;
        return result;
    }

    private int getMin(int matrix, int matrix1, int matrix2) {
        return Math.min(Math.min(matrix,matrix1), matrix2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}