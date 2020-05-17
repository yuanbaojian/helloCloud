package com.ybj.crawler.utils;

import java.io.IOException;

/**
 * @Author changePassword
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class changePassword {

    public static void main(String[] args) throws IOException {
        Process exec = Runtime.getRuntime().exec(new String[]{"cmd", "/K", "Start", "ipconfig"});
        System.out.println("exec = " + exec);
    }

}
