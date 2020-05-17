package com.ybj.crawler.utils.Crawler.Proxy;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Proxy {

    @Test
    public void Test() {
        try {
            URLConnection httpCon = new URL("https://www.google.com/").openConnection();
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            System.out.println("code = " + code);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
