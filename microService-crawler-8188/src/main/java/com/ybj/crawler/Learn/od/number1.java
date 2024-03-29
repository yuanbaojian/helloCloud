package com.ybj.crawler.Learn.od;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
/*
* 有一个字符串数组words和一个字符串chars。
假如可以用chars中的字母拼写出words中的某个“单词”（字符串），那么我们就认为你掌握了这个单词。
words的字符仅由 a-z 英文小写字母组成。 例如: abc
chars 由 a-z 英文小写字母和 “?”组成。其中英文问号“?”表示万能字符，能够在拼写时当做任意一个英文字母。 例如： "?" 可以当做 "a"等字母。
注意：每次拼写时，chars中的每个字母和万能字符都只能使用一次。
输出词汇表words中你掌握的所有单词的个数。 没有掌握任何单词，则输出0。*/
public class number1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        while(in.hasNext()){
            int wordNumber = in.nextInt();
            in.nextLine();
            List<String> wordList = new LinkedList<>();
            for (int i = 0; i < wordNumber; i++) {
                wordList.add(in.nextLine());
            }
            String charContent = in.nextLine();
            char[] charContentArray = charContent.toCharArray();
            AtomicInteger number = new AtomicInteger();

            wordList.forEach( a->{
                //  这里内存占用太多，可以优化
                List<String> charListTemp =  new LinkedList<>();
                for (char c : charContentArray) {
                    charListTemp.add(String.valueOf(c));
                }
                boolean flag = true;
                char[] charArray = a.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    if (charListTemp.contains(String.valueOf(charArray[i]))){
                        charListTemp.remove(String.valueOf(charArray[i]));
                    } else if( charListTemp.contains("?"))  {
                        charListTemp.remove("?");
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    number.getAndIncrement();
                }
            });
            System.out.println("number.get() = " + number.get());

        }
    }
}