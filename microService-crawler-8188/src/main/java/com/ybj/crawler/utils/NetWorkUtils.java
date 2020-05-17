package com.ybj.crawler.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWorkUtils {

    public static String getPageContent(String urlPath, String requestMethod) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
        conn.setConnectTimeout(5000);
        if (conn.getResponseCode() == 200) {
            String pageContent = IOUtils.toString(conn.getInputStream(), "utf-8");
            return pageContent;
        } else {
            return "请求失败";
        }
    }

    public static String getPageContentWithParam(String urlPath, String requestMethod) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("loginName", "admin");
        conn.setRequestProperty("password", "admin");
        conn.setRequestProperty("systemType", "0");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
        conn.setConnectTimeout(5000);
        if (conn.getResponseCode() == 200) {
            String pageContent = IOUtils.toString(conn.getInputStream(), "utf-8");
            return pageContent;
        } else {
            return "请求失败";
        }
    }

    public static void main(String[] args) throws IOException {
        String pageContent = getPageContent("http://192.168.6.43:8889/CBTSystem/auth/login", "POST");
        System.out.println("pageContent = " + pageContent);
    }
}
