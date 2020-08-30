package com.ybj.crawler.utils.Crawler.FIleDownload;


import com.ybj.crawler.utils.NetWorkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author Jsoup
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
public class downloadZuiYou {

    public static void main(String[] args) throws Exception {
        String urlPrefix="http://www.budejie.com/";
        int pageNumber=100;
        for (int i = 10; i < pageNumber; i++) {
            String url=urlPrefix+i;
            log.info("URL为 {}", url);
            startDownload(url);
            System.out.println("第 "+i+" 页下载完成" );
        }
    }



    public static void startDownload(String urlString) throws Exception {
        URL url=new URL(urlString);
        String urlSource = getURLSource(url);
        Document doc= Jsoup.parse(urlSource);
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容
        Elements elements = doc.select("div[class=j-r-list-c-img]").select("img");
        for (int i = 0; i <elements.size(); i++) {
            String targetURL = elements.get(i).attr("data-original");
            log.info("图片URL {}",targetURL);
            download(targetURL);
            System.out.println("第 "+ i+ "项----" + targetURL);
        }

    }

    public static void download(String urlString) throws IOException {
        String descPrefix="G:\\clawer\\budejie\\";
        String fileName=urlString.substring(urlString.lastIndexOf("/") +1,urlString.length());
        String filePath=descPrefix+fileName;
        URL url=new URL(urlString);
        FileUtils.copyURLToFile(url,new File(filePath) );
    }

    @Test
    public void GetSomeSpecificURLSource() throws Exception {
        String src="https://shanghaicity.openservice.kankanews.com/public/bus/mes/sid/7019f275eae92b302744ade1ac88763a";
        URL url=new URL(src);
        String urlSource = NetWorkUtils.getPageContent(src,"GET");
        System.out.println("urlSource = " + urlSource);
    }


    @Test
    public void getBusInfl() throws Exception {
        String src="https://shanghaicity.openservice.kankanews.com/public/bus/Getstop?stoptype=0&stopid=10&sid=7019f275eae92b302744ade1ac88763a";
        URL url=new URL(src);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
        String htmlSource = new String(data);
        System.out.println("htmlSource = " + htmlSource);
    }


    /**   解析html源码
     * @param url
     * @return
     * @throws Exception
     */
    public static String getURLSource(URL url) throws Exception    {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
        String htmlSource = new String(data);
        return htmlSource;
    }

    /** *//**
     * 把二进制流转化为byte字节数组
     * @param instream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream instream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[]  buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        instream.close();
        return outStream.toByteArray();
    }
}
