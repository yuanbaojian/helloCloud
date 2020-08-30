package com.ybj.crawler.utils.Crawler;

import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.model.IpBeanList;
import com.ybj.crawler.utils.NetWorkUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.net.*;
import java.util.List;

/**
 * @author yuanbaojian
 */
public class IPUtils {

    /**
    * <p>
     *     构造方法私有化，使得外部无法实例化
    * </p>
     * @param
     * @return
     * @author yuanbaojian
     * @date 2020/8/19
     * @time 23:43
     */
    private IPUtils(){

    }

    //饿汉模式， 上来就实例化，比较消耗资源
    // private static IPUtils ipUtils = new IPUtils();

    /**
    * <p>懒汉模式，双重校验
     * volatile作用: 避免空指针异常（可能成员变量未实例化, 就返回了实例对象）
     * </p>
     * @author yuanbaojian
     * @date 2020/8/19
     * @time 23:13
     */
    private static volatile IPUtils ipUtils = null;

    public static IPUtils  getInstance(){
        if(ipUtils == null){
            synchronized (IPUtils.class){
                if(ipUtils == null){
                    ipUtils = new IPUtils();
                }
            }}
        return ipUtils;
    }

    private static String HTTP_API = "https://www.xicidaili.com/wt/";

    public  boolean checkIpAvailable(IpBean ipBean) {
        if(ipBean.getIpAddress() == null){
            return false;
        }
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIpAddress(), ipBean.getIpPort()));
        try {
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            // System.out.println(code);
            return code == 200;
        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println(ipBean.getIpAddress() + "  无效");
        }
        return false;
    }

    public static boolean isValid(IpBean ipBean) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIpAddress(), ipBean.getIpPort()));
        try {
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            // System.out.println(code);
            return code == 200;
        } catch (IOException e) {
            // System.out.println(ipBean.getIpAddress() + "  无效");
        }
        return false;
    }

    public static boolean isValidByHttpClient(IpBean ipBean) {
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        HttpHost proxy = new HttpHost("144.121.255.37", 8080, "http");
        try(
                CloseableHttpClient httpclient = HttpClientBuilder.create().setProxy(proxy).build();
                CloseableHttpResponse response = httpclient.execute(httpGet);
        ) {
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }




    public static String decodeIp(String ip) throws IOException {
        String result = "";
        String filteredIp = ip.split("\"")[1];
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(filteredIp);
        result = new String(bytes, "utf-8");
        return result;
    }


    public  boolean checkIpValid(IpBean ipBean) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIpAddress(), ipBean.getIpPort()));
        try {
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            System.out.println(code);
            return code == 200;
        } catch (IOException e) {
            System.out.println(ipBean.getIpAddress() + "  无效");
        }
        return false;
    }



}
