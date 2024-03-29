package com.ybj.crawler.Learn.od;


import java.util.*;
import java.util.stream.Collectors;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
/*
*
小明今年升学到小学一年级，来到新班级后发现其他小朋友们身高参差不齐，然后就想基于各小朋友和自己的身高差对他们进行排序，请帮他实现排序。
*
* 第一行为正整数H和N，0<H<200，为小明的身高，0<N<50，为新班级其他小朋友个数。
第二行为N个正整数H1-HN，分别是其他小朋友的身高，取值范围0<Hi<200（1<=i<=N），且N个正整数各不相同。
* 
* 输出排序结果，各正整数以空格分割。和小明身高差绝对值最小的小朋友排在前面，和小明身高差绝对值最大的小朋友排在最后，如果两个小朋友和小明身高差一样，则个子较小的小朋友排在前面。

* */
public class number2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) {
            int H = in.nextInt();
            int N = in.nextInt();
            List<Person> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int height = in.nextInt();
                int absHeightDelete = Math.abs(H - height);
                list.add(new Person(height, absHeightDelete));
            }
            List<Person> collect = list.stream().sorted(Comparator.comparing(Person::getAbsHeightDelete).thenComparing(Person::getHeight)).collect(Collectors.toList());
            for (Person person : collect) {
                System.out.print(person.height + " ");
            }
        }


    }

    static class Person {
        int height;
        int absHeightDelete;

        public Person(int height, int absHeightDelete) {
            this.height = height;
            this.absHeightDelete = absHeightDelete;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getAbsHeightDelete() {
            return absHeightDelete;
        }

        public void setAbsHeightDelete(int absHeightDelete) {
            this.absHeightDelete = absHeightDelete;
        }
    }
}