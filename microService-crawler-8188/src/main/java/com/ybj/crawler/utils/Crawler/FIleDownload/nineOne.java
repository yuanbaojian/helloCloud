package com.ybj.crawler.utils.Crawler.FIleDownload;


import com.ybj.crawler.utils.NetWorkUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author nineOne
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class nineOne {

    private static String urlPrefix="http://www.91porn.com/video.php?category=rf&page=";

    public static Integer number=0;

    public static void main(String[] args) throws Exception {
        int pageNumber=10;
        for (int i = 4; i < pageNumber; i++) {
            String url=urlPrefix+i;
            getPageURL(url);
            System.out.println("第 "+i+" 页下载完成" );
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
        Elements elements = doc.select("div[class=listchannel]").select("a");
        for (int i = 0; i <elements.size(); i++) {
            String targetURL = elements.get(i).attr("href");
            startDownload( targetURL);
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
        Elements elements = doc.select("video[class=vjs-tech]").select("source");
        for (int i = 0; i <elements.size(); i++) {
            String urlString = elements.get(i).attr("src");
            urlString=urlString.substring(0, urlString.indexOf("?"));
            downloadPhoto(urlString);
        }

    }


    /**
     * 文件流形式下载， 防止403报错
     * @param urlString
     * @throws IOException
     */
    private static void downloadPhoto(String urlString) {
        String descPrefix="G:\\clawer\\91\\videos\\";
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
