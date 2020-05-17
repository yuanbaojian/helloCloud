package com.ybj.crawler.Learn.ThinkingInJava.Ch18FILE;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IO {

    @Test
    public void testBufferReader() throws IOException {
        String fileSrc="C:\\Users\\baojian.yuan\\Desktop\\1.txt";
        File file;
        BufferedReader in=new BufferedReader(new FileReader(fileSrc));
        String s;
        while((s=in.readLine())!=null){
            System.out.println("s = " + s);
        }
    }
}
