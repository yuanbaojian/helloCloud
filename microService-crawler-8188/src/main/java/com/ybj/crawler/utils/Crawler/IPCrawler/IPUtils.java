package com.ybj.crawler.utils.Crawler.IPCrawler;

import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.model.IpBeanList;
import com.ybj.crawler.utils.NetWorkUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class IPUtils {

    private static String HTTP_API = "https://www.xicidaili.com/wt/";

    public static boolean isValid(IpBean ipBean) {
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


    @Test
    public void testGetRequestIP() throws IOException {
        String urlString = "http://yuanbaojian.xyz/";
        URL url = new URL(urlString);
        HttpURLConnection conn = null;
        Proxy proxy = null;
        if (IpBeanList.getSize() != 0) {
            List<IpBean> ipBeanList1 = IpBeanList.getIpBeanList();
            String ipAddress = ipBeanList1.get(0).getIpAddress();
            Integer ipPosrt = ipBeanList1.get(0).getIpPort();
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipAddress, ipPosrt));
        }
        if (proxy != null) {
            conn = (HttpURLConnection) url.openConnection(proxy);

        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
        try {
            // Fetch IP address by getByName()
            InetAddress ip = InetAddress.getByName(new URL(urlString)
                    .getHost());
            // Print the IP address
            System.out.println("Public IP Address of: " + ip);
        } catch (MalformedURLException e) {
            // It means the URL is invalid
            System.out.println("Invalid URL");
        }
    }

    @Test
    public void testGetServerIp() throws IOException {
        String urlString = "http://current.ip.16yun.cn:802/";
        String pageContent = NetWorkUtils.getPageContent(urlString, "GET");
        System.out.println("pageContent = " + pageContent);
    }

}
