package com.ybj.crawler.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.crawler.dao.IpBeanMapper;
import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanService;
import com.ybj.crawler.utils.Crawler.IPCrawler.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ybj
 * @since 2020-02-10
 */
@Slf4j
@Service
public class IpBeanServiceImpl extends ServiceImpl<IpBeanMapper, IpBean> implements IpBeanService {

    @Autowired
    RedisTemplate redisTemplate;

    // 默认爬取前两页
    private final Integer pageNumber = 1;

    private static String HTTP_API = "https://www.xicidaili.com/wt/";


    @Override
    public List<IpBean> getIpFromWeb() throws IOException {
        List<IpBean> ipBeanList = new LinkedList<>();
        for (int i = 1; i <= pageNumber; i++) {
            try {
                ipBeanList.addAll(getIpList(HTTP_API + i));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ipBeanList;
    }

    @Async
    @Override
    public Set<IpBean> getValidIpByAsyncMethod() {
        Set<IpBean> ipBeans = redisTemplate.opsForSet().members("validIpSet");
        Set<IpBean> validIpBeanSet = new HashSet<>();
        for (IpBean ipBean : ipBeans) {
            boolean valid = IPUtils.isValid(ipBean);
            System.out.println(String.format("%s + valid = " + valid, ipBean.getIpAddress()));
            if (valid) {
                validIpBeanSet.add(ipBean);
            }
        }
        return validIpBeanSet;
    }

    @Override
    public Set<IpBean> getValidIpByThread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Set<IpBean> ipBeansFromRedis = redisTemplate.opsForSet().members("validIpSet");
        for (IpBean ipBean : ipBeansFromRedis) {
            Future<IpBean> submit = (Future<IpBean>) executorService.submit(() -> {
                boolean valid = IPUtils.isValid(ipBean);
                System.out.println("valid = " + valid);
                if (valid) {
                    System.out.println(ipBean.toString() + "is valid");
                } else {
                    redisTemplate.opsForSet().remove("validIpSet", ipBean);
                    ipBeansFromRedis.remove(ipBean);
                    System.out.println("已移除 " + ipBean.toString());
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ipBeansFromRedis;
    }


    private List<IpBean> getIpList(String urlString) throws IOException, ExecutionException, InterruptedException {
        List<IpBean> ipBeanList = new LinkedList<>();
        URL url = new URL(urlString);
        HttpURLConnection conn = null;
        Proxy proxy = null;
//        网站应该做了反爬取， 爬取有问题
//        Set<IpBean> validIpSet = getValidIpByThread();
//        IpBean validIpBean = null;
//        if (validIpSet.size() != 0) {
//            validIpBean = (IpBean) redisTemplate.opsForSet().randomMember("validIpSet");
//            String ipAddress = validIpBean.getIpAddress();
//            Integer ipPosrt = validIpBean.getIpPort();
//            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipAddress, ipPosrt));
//        }
        // 使用ip代理
        if (proxy != null) {
            conn = (HttpURLConnection) url.openConnection(proxy);
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setConnectTimeout(10000);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        String urlSource = IOUtils.toString(conn.getInputStream(), "utf-8");
        Document doc = Jsoup.parse(urlSource);
        Elements elements = doc.select("tr");
        

        // 之前的无参数，无返回值。 可以运行，但是要是有参数或者返回值，怎么弄
        ExecutorService executorService = Executors.newCachedThreadPool();
        LinkedList<Future<IpBean>> validIpFromWeb = new LinkedList<>();
        for (int i = 0; i < elements.size(); i++) {
            //跳过表头
            if (i == 0) {
                continue;
            }
            IpBean ipBean = new IpBean();
            setIpBeanAttribute(elements, i, ipBean);
            ipBeanList.add(ipBean);
            Future<IpBean> future = executorService.submit((Callable<IpBean>) () -> {
                boolean valid = IPUtils.isValid(ipBean);
                if (valid) {
                    redisTemplate.opsForSet().add("validIpSet", ipBean);
                    log.info(ipBean.getIpAddress() + " 有效，已存入redis");
                    return ipBean;
                } else {
                    return null;
                }
            });
            validIpFromWeb.add(future);
        }
        executorService.shutdown();
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
}
