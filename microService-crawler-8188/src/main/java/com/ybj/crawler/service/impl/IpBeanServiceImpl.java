package com.ybj.crawler.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.auth.feign.UserServiceApi;
import com.ybj.crawler.dao.IpBeanMapper;
import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanAsyncService;
import com.ybj.crawler.service.IpBeanService;
import com.ybj.crawler.service.IpBeanThreadService;
import com.ybj.crawler.utils.Crawler.IPUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.util.*;
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

    @Autowired
    IpBeanThreadService ipBeanThreadService;

    @Autowired
    IpBeanMapper ipBeanMapper;

    @Autowired
    IpBeanAsyncService ipBeanAsyncService;

    @Autowired
    UserServiceApi userServiceApi;

    // 默认爬取前两页
    private final Integer pageNumber = 1;

    private static String HTTP_API = "https://www.xicidaili.com/wt/";

    private static String FREE_PROXY_API = "http://free-proxy.cz/zh/";


    @Override
    public List<IpBean> getIpFromWeb() throws IOException {
        List<IpBean> ipBeanList = new LinkedList<>();
        for (int i = 1; i <= pageNumber; i++) {
            ipBeanList.addAll(getIpListByThreadPool(HTTP_API + i));
        }
        return ipBeanList;
    }

    @Override
    public Set<IpBean> getValidIpByAsyncMethod() throws InterruptedException {
        Set<IpBean> ipBeansInRedis = redisTemplate.opsForSet().members("validIpSet");
        log.info("从redis中获得到的 ip {},长度为 {}", ipBeansInRedis, ipBeansInRedis.size());
        // 使用countDownLatch 计数
        CountDownLatch countDownLatch = new CountDownLatch(ipBeansInRedis.size());
        for (IpBean ipBean : ipBeansInRedis) {
            ipBeanAsyncService.filterIp(ipBean , ipBeansInRedis,countDownLatch);
        }
        countDownLatch.await();
        return ipBeansInRedis;
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

    @SneakyThrows
    public List<IpBean> getIpListByThreadPool(String urlString) throws IOException {
        List<IpBean> ipBeanList = new LinkedList<>();
        URL url = new URL(urlString);
        HttpURLConnection conn = null;
        Proxy proxy = null;
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
        List<CompletableFuture<IpBean>> futureList = new LinkedList<>();
        for (int i = 1; i < elements.size()-1; i++) {
            CompletableFuture<IpBean> ipBeanFuture = ipBeanThreadService.checkIpValidByThreadPool(elements, i);
            futureList.add(ipBeanFuture);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        for (CompletableFuture<IpBean> ipBeanCompletableFuture : futureList) {
            ipBeanList.add(ipBeanCompletableFuture.get());
        }
        return ipBeanList;
    }



    @Async
    @Override
    public Future<String> executeAsyncTaskWithResult(String param) {
        log.info("线程" + Thread.currentThread().getName() + " 执行异步任务：" + param);
        return new AsyncResult<>(param);
    }

    @Async
    @Override
    public void expandNumber(IpBean ipBean) {
        ipBean.setID(null);
        ipBeanMapper.insert(ipBean);
    }

    @Override
    public String getIpFromFreeProxy() throws IOException, ExecutionException, InterruptedException {
        Random random = new Random();
        int startPage = random.nextInt(20);
        String websiteURL = "";
        int pageNumber = 3;
        if(pageNumber ==1) {
            websiteURL = FREE_PROXY_API  + pageNumber;
        } else{
            websiteURL = FREE_PROXY_API + "proxylist/main/" + pageNumber;
        }
        log.info("准备爬取 {}", websiteURL);
        //这个方法不是异步的！！！！
        String result = getIpFromFreePoxy(websiteURL);
        return result;
    }

    @Override
    public Boolean testRetryable() throws Exception {
        log.info("正在请求testRetryable");
        Boolean result = userServiceApi.checkUser("ybj");
        log.info("请求testRetryable结束 result: {}", result);
        return result;
    }


    /**
    * <p>
     *     从 free-proxy网站爬取IP代理
    * </p>
     * @param websiteURL
     * @return java.util.List<com.ybj.crawler.model.IpBean>
     * @author yuanbaojian
     * @date 2020/8/18
     * @time 16:47
     */
    private String getIpFromFreePoxy(String websiteURL) throws IOException, ExecutionException, InterruptedException {
        URL url = new URL(websiteURL);
        Elements elements = new Elements();
        // 从redis中获得 存储的样本element
        String docInRedis = (String) redisTemplate.opsForValue().get("freeProxyDocSample");;
        if(docInRedis == null){
            log.info("redis缓存的doc样本为空， 直接访问FP网址: {}" , url.toString());
            //TODO 需要设置代理，否则第二个请求无法发送
            Document doc =  Jsoup.parse(url, 10000);
            redisTemplate.opsForValue().set("freeProxyDocSample", doc.toString());
            docInRedis = doc.toString();
            log.info("已将doc样本存入到Redis中");
        }
        Document doc = Jsoup.parse(docInRedis);
        elements = doc.select("tr");
        log.info("redis中缓存的 FP的element元素为 不为空");
        log.info("共{}条element记录", elements.size());
        List<CompletableFuture<Boolean>> futureList = new LinkedList<>();
        for (int i = 0; i < elements.size(); i++) {
            log.info("正在检测第{}条记录",i);
            IpBean ipBean = new IpBean();
            setIpBeanAttributeForFP(elements.get(i),  ipBean);
            if(ipBean.getIpAddress() != null){
                CompletableFuture<Boolean> result = ipBeanAsyncService.checkIpValidAndStoreByCF(ipBean);
                futureList.add(result);
            }
        }
        //线程join，等待执行完毕
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        int sum = elements.size();
        int failedCount = 0;

        for (CompletableFuture<Boolean> booleanCompletableFuture : futureList) {
            log.info("future队列长度为 {}",futureList.size());

            Boolean aBoolean = booleanCompletableFuture.get();
            if(!aBoolean){
                failedCount++;
            }
        }
        log.info("共计{}条记录，失败次数 {}",sum,failedCount);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("共计").append(sum).append("条");
        stringBuffer.append("无效条数").append(failedCount).append("条");
        return stringBuffer.toString();
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

    private static void setIpBeanAttributeForFP(Element element, IpBean ipBean) throws IOException {
        String encodedIpAddress = element.getElementsByTag("script").html();
        if(!encodedIpAddress.contains("Base64")){
            log.info("该条element不含有ip信息, 加密ip为 {}",encodedIpAddress);
            return;
        }
        String ipAddress = IPUtils.decodeIp(encodedIpAddress);
        //端口
        Integer ipPort = Integer.valueOf(element.getElementsByClass("fport").html());
        //服务器所在地
        String serverAddress = element.getElementsByTag("a").html();
        //匿名类型
        String anonymityType = element.getElementsByTag("small").get(3).html();
        //协议
        String protocolType = element.getElementsByTag("small").get(0).html();
        //可用性先不考虑
        Element available = element.getElementsByTag("small").get(5);

        ipBean.setIpAddress(ipAddress);
        ipBean.setIpPort(ipPort);
        ipBean.setAnonyType(anonymityType);
        ipBean.setProtocolType(protocolType);
        ipBean.setServerAddress(serverAddress);
    }


}
