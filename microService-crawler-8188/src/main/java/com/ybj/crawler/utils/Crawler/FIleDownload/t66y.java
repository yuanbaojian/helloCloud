package com.ybj.crawler.utils.Crawler.FIleDownload;


import com.ybj.crawler.utils.NetWorkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jsoup
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
public class t66y {

    public static String URLPrefix="http://t66y.com/";

    public static Integer number=0;

    public static void main(String[] args) throws Exception {
        String urlPrefix="http://t66y.com/thread0806.php?fid=7&search=&page=";
        int pageNumber=2;
        for (int i = 1; i <=pageNumber; i++) {
            String url=urlPrefix+i;
            getPageURL(url);
            System.out.println("第 "+i+" 页下载完成" );
            Thread.sleep(10000);
        }
    }


    /**
     * 获得某一个具体页面URL
     * @param urlString
     * @throws Exception
     */
    public static void getPageURL(String urlString) throws Exception {
        String urlSource = NetWorkUtils.getPageContent(urlString,"GET");
        Document doc= Jsoup.parse(urlSource);
        Elements elements = doc.select("td[class=tal]").select("H3").select("a");
        for (int i = 0; i <elements.size(); i++) {
            String targetURL = elements.get(i).attr("href");
            startDownload(URLPrefix + targetURL);
        }


    }


    /**
     * 获得下载链接
     * @throws IOException
     */
    public static void startDownload(String pageURL) throws IOException {
        String urlSource = NetWorkUtils.getPageContent(pageURL,"GET");
        Document doc= Jsoup.parse(urlSource);
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容
        Elements elements = doc.select("div[class=tpc_content do_not_catch]").select("img");
        ExecutorService executorService = Executors.newFixedThreadPool(30);

        for (int i = 0; i <elements.size(); i++) {
            String urlString = elements.get(i).attr("ess-data");
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    if(urlString.startsWith("https")){
                        downloadPhoto(urlString);
                    }
                }
            });

        }

    }


    /**
     * 文件流形式下载， 防止403报错
     * @param urlString
     * @throws IOException
     */
    private static void downloadPhoto(String urlString) {
        log.info("图片URL是 {}",urlString);
        String descPrefix="/data/photos";
        String fileName= FilenameUtils.getName(urlString);
        File picutreFile = new File(descPrefix + fileName);
        try {
            URL url2=new URL(urlString);
            URLConnection conn = url2.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
            conn.connect();
            FileUtils.copyInputStreamToFile(conn.getInputStream(), picutreFile);
            picutreFile.createNewFile();
            number++;
            System.out.println("第 "+ number + "项----" + urlString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
