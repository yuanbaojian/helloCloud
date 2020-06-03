package com.ybj.crawler.Learn.ThinkingInJava.Ch18FILE;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Author DirList2
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DirList2 {
    public static void main(String[] args) {
        File path = new File(".");

        String[] list;
        if(args.length == 0){
            list = path.list();
        } else {
            list = path.list(filter(args[0]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String s : list) {
            System.out.println("s = " + s);
        }
    }

    //静态方法
    static  FilenameFilter filter(final String regex) {
       return new FilenameFilter() {

           private Pattern pattern = Pattern.compile(regex);

           //override重载
           @Override
           public boolean accept(File dir, String name) {
               return pattern.matcher(name).matches();
           }
       };
    }

}
