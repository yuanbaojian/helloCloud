package com.ybj.crawler.Learn.od;

import java.util.Scanner;

/*
* A、B两个人玩抢7游戏，游戏规则为A先报一个起始数字X(10<起始数字<10000)，B报下一个数字Y(X-Y<3),A再报一个数字Z(Y-Z<3)，以此类推，直到其中一个抢到7，抢到7即为胜者；在B赢得比赛的情况下，一共有多少种组合？
*
* */
public class number3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
          // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) {
            int X = in.nextInt();
            int count = 0;
            for (int i = X - 2; i < X; i++) {
                for (int j = i - 2; j < i; j++) {
                    for (int k = j - 2; k < j; k++) {
                        for (int l = k - 2; l < k; l++) {
                            for (int m = l - 2; m < l; m++) {
                                for (int n = m - 2; n < m; n++) {
                                    if (n == 7) {
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("count = " + count);
    }
}
}