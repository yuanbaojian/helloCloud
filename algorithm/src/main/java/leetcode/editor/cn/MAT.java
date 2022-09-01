package leetcode.editor.cn;
/*         [[1,1,1,1],
            [1,1,1,1],
            [1,1,1,1],
            [1,1,1,1]]

 */


public class MAT {

    public static void main(String[] args) {
        //int[][] array= {
        //        {1,2,3},
        //        {4,5,6},
        //        {7,8,9}};
        //int[][] array=
        //        {{1,1,1,1},
        //                {1,1,1,1},
        //                {1,1,1,1},
        //                {1,1,1,1}};
        int[][] array=
                {{5}};
        //边长
        int length = array.length;
        int sum = 0;

        // 获得下标
        for (int i = 0; i < length; i++) {
            int leftColumnIndex = i;
            int rightColumnIndex = length - 1 -i;
            sum += array[i][leftColumnIndex] ;
            sum += array[i][rightColumnIndex];
        }
        //如果是基数列，需要减去重复相加的项
        if(length%2==1){
            int index = length / 2;
            sum -= array[index][index];
        }
        System.out.println("sum = " + sum);
    }
}
