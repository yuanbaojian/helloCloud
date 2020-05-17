package com.ybj.crawler.utils.Crawler.IPCrawler;

import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.model.IpBeanList;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.RedisTemplate;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class IPCrawler {
    private static String HTTP_API = "https://www.xicidaili.com/wt/";
    private static String HTTPS_API = "https://www.xicidaili.com/wn/";
    static String httpsUrl="https://www.xicidaili.com/wt/";

    public static RedisTemplate redisTemplate;

    public static void main(String[] args) throws IOException {

        int pageNumber=2;
        List<IpBean> IpBeans = getIpBeanList(HTTP_API,pageNumber, redisTemplate);
        List<IpBean> availableIp = getAvailableIp(IpBeans);
        for (IpBean IpBean : availableIp) {
          System.out.println("IpBean.toString() = " + IpBean.toString());
        }
        System.out.println("availableIp = " + availableIp);

    }

    @Test
    public void test(){
        RedisTemplate redisTemplate = new RedisTemplate();
        List<IpBean> ipBeans = new LinkedList<>();
        IpBean ipBean1 = new IpBean();
        ipBean1.setIpAddress("1");
        IpBean ipBean2 = new IpBean();
        ipBean2.setIpAddress("1");
        ipBeans.add(ipBean1);
        ipBeans.add(ipBean2);
        redisTemplate.opsForValue().set("IpList", ipBeans );
        List<IpBean> ipList = (List<IpBean>) redisTemplate.opsForValue().get("IpList");
    }

    public static List<IpBean> getIpList(String protocolType, int pageNumber) throws IOException {
        List<IpBean> IpBeans = getIpBeanList(protocolType,pageNumber, redisTemplate);
        //List<IpBean> availableIp = getAvailableIp(IpBeans);
        return IpBeans;
    }

    public static List<IpBean> getAvailableIp(List<IpBean> ipBeans){
        List<IpBean> availableIPList=null;
        for (IpBean ipBean : ipBeans) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean valid = IPUtils.isValid(ipBean);
                    if (valid == true) {
                        IpBeanList.add(ipBean);
                        System.out.println("有效 = " + ipBean.getIpAddress() + "  " + ipBean.getIpPort());
                    }
                    IpBeanList.increase();
                }
            }).start();

        }
        while (true) {
            if (IpBeanList.getCount() == ipBeans.size()) {
                System.out.println("共爬取到  " + ipBeans.size() + " 个IP");
                System.out.println("共爬取到  " + IpBeanList.getSize() + " 个有用的IP");
                availableIPList= IpBeanList.getIpBeanList();
                break;
            }
        }
        return availableIPList;
    }

    public static void pushSingleAvailableIP(IpBean ipBean) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean valid = IPUtils.isValid(ipBean);
                if (valid == true) {
                    IpBeanList.add(ipBean);
                }
                IpBeanList.increase();
            }
        }).start();

    }

    public static List<IpBean> getIpBeanList(String urlString, int pageNumber, RedisTemplate redisTemplateFromController) throws IOException {
        List<IpBean> ipBeanList=new LinkedList<>();
        redisTemplate = redisTemplateFromController;
        for(int i=1; i<=pageNumber;i++){
            ipBeanList.addAll(startCrawl(urlString + i));
        }
        return ipBeanList;
    }


    public static List<IpBean> startCrawl(String  urlString) throws IOException {
        List<IpBean> ipBeanList = new LinkedList<>();
        URL url = new URL(urlString);
        HttpURLConnection conn = null;
        Proxy proxy = null;
        List<IpBean> validIpList = null;//redisTemplate.opsForList().range("validIpList", 0, -1);
        IpBean ip=null;
        boolean ipValid = false;
        if (validIpList != null) {
            ip = validIpList.get(0);
            if (IPUtils.isValid(ip)) {
                ipValid = true;
            }
        }
        if (ipValid) {
            List<IpBean> ipBeanList1 = IpBeanList.getIpBeanList();
            String ipAddress = ip.getIpAddress();
            Integer ipPosrt = ip.getIpPort();
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipAddress, ipPosrt));
        }
        if (proxy != null) {
            conn = (HttpURLConnection) url.openConnection(proxy);
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");

        String urlSource = IOUtils.toString(conn.getInputStream(), "utf-8");
        Document doc = Jsoup.parse(urlSource);
        Elements elements = doc.select("tr");
        for (int i = 0; i < elements.size(); i++) {
            if (i == 0) {
                continue;
            }
            IpBean ipBean = new IpBean();
            setIpBeanAttribute(elements, i, ipBean);
            ipBeanList.add(ipBean);
            boolean valid = IPUtils.isValid(ipBean);
            if(valid){
                IpBeanList.add(ipBean);
//                redisTemplate.opsForList().leftPush("validIpList", ipBean);
                IpBeanList.increase();;
            }
        }
        return ipBeanList;
    }

    private static void setIpBeanAttribute(Elements elements, int i, IpBean ipBean) {
        String ipAddress = elements.get(i).children().get(1).text();
        Integer ipPort = Integer.valueOf(elements.get(i).children().get(2).text().trim());
        String serverAddress = elements.get(i).children().get(3).text();
        String anonymityType = elements.get(i).children().get(4).text();
        String protocolType = elements.get(i).children().get(5).text();
        ipBean.setIpAddress(ipAddress);
        ipBean.setIpPort(ipPort);
        ipBean.setAnonyType(anonymityType);
        ipBean.setProtocolType(protocolType);
        ipBean.setServerAddress(serverAddress);
    }

    public static List<IpBean> startCrawlWithProxy(String urlString) throws IOException {
        List<IpBean> ipBeanList = new LinkedList<>();
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

        String urlSource = IOUtils.toString(conn.getInputStream(), "utf-8");
        Document doc = Jsoup.parse(urlSource);
        Elements elements = doc.select("tr");
        for (int i = 0; i < elements.size(); i++) {
            if (i == 0) {
                continue;
            }
            IpBean ipBean = new IpBean();
            String ipAddress = elements.get(i).children().get(1).text();
            Integer ipPort = Integer.valueOf(elements.get(i).children().get(2).text().trim());
            ipBean.setIpAddress(ipAddress);
            ipBean.setIpPort(ipPort);
            ipBeanList.add(ipBean);
            pushSingleAvailableIP(ipBean);
        }
        return ipBeanList;
    }


}
