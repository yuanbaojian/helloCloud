package com.ybj.crawler.utils.Crawler.FIleDownload;


import com.fasterxml.jackson.databind.JsonNode;
import com.ybj.api.utils.JacksonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author downloadPexels
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class downloadPexels {
    public static String pexeclURL = "https://www.pexels.com/zh-cn/search/";
    public static String baiduURL = "https://www.baidu.com/";
    public static String pexeclSelector = "img[class=photo-item__img]";
    public static String pexeclSelectorAttr = "srcset";
    public static String pexeclDescPrefix = "G:\\clawer\\pexel\\";

    public static String pbudejieURL = "http://www.budejie.com/hot/2";
    public static String budejieSelector = "img[class=lazy]";
    public static String budejieSelectorAttr = "data-original";
    public static String budejieDescPrefix = "C:\\Users\\yuanbaojian\\Desktop\\desc\\budejie\\hot\\";


    public static String nineOnePornURL = "http://198.255.82.91/index.php";
    public static String nineOnePornSelector = "a[target=blank]";
    public static String nineOnePornSelectorAttr = "href";
    public static String nineOnePornDescPrefix = "C:\\Users\\yuanbaojian\\Desktop\\desc\\nineOne\\video\\";

    public static String googlelURL = "https://www.google.com/";
    public static Integer number = 0;
    public static final String pexelApiKey = "563492ad6f91700001000001b5fd4659e7774dc1b41e8d055e043a2a";


    @Test
    public void testIOUtils() throws IOException {
        String keyword = "spring";
        String fullUrl = "https://api.pexels.com/v1/search?query=" + keyword + "&per_page=15&page=1";
        String jsonResult = getJsonResult(fullUrl);
        JsonNode jsonNode = JacksonUtils.stringToJsonNode(jsonResult);
        JsonNode photoResult = jsonNode.get("photos");
        for (JsonNode node : photoResult) {
            String phtotURL = String.valueOf(node.get("src").get("original"));
            System.out.println("phtotURL = " + phtotURL);
            download(phtotURL, pexeclDescPrefix);
        }
        //    download(photoURL, pexeclDescPrefix);

    }

    private static String getJsonResult(String pexeclURL) throws IOException {
        URL url = new URL(pexeclURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", pexelApiKey);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
        conn.setConnectTimeout(5000);
        if (conn.getResponseCode() == 200) {
            String pageContent = IOUtils.toString(conn.getInputStream(), "utf-8");
            return pageContent;
        } else {
            return "请求失败";
        }
    }

    public static void main(String[] args) throws IOException {
        String keyword = "debug";
        String fullUrl = "https://api.pexels.com/v1/search?query=" + keyword + "&per_page=55&page=1";
        String jsonResult = getJsonResult(fullUrl);
        JsonNode jsonNode = JacksonUtils.stringToJsonNode(jsonResult);
        JsonNode photoResult = jsonNode.get("photos");
        for (JsonNode node : photoResult) {
            String phtotURL = node.get("src").get("original").asText();
            System.out.println("phtotURL = " + phtotURL);
            download(phtotURL, pexeclDescPrefix + keyword + File.separatorChar);
        }
    }

    private static void download(String photoURL, String pexeclDescPrefix) {
        String fileName = FilenameUtils.getName(photoURL);
        File dir = new File(pexeclDescPrefix);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File picutreFile = new File(pexeclDescPrefix + fileName);
        try {
            picutreFile.createNewFile();
            URL photoUrl = new URL(photoURL.trim());
            URLConnection conn = photoUrl.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
            conn.setRequestProperty("Authorization", pexelApiKey);
            conn.connect();
            FileUtils.copyInputStreamToFile(conn.getInputStream(), picutreFile);
            number++;
            System.out.println("第 " + number + "项----" + photoURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单次下载案例
     *
     * @return void
     * @author yuanbaojian
     * @date 2020/4/3
     * @time 15:12
     */
    @Test
    public void downLoadDemo() {
        String photoUrl = "https://images.pexels.com/photos/842711/pexels-photo-842711.jpeg";
        String fileTarget = "C:\\Users\\yuanbaojian\\Desktop\\";
        download(photoUrl, fileTarget);
    }

    @Test
    public void test() throws IOException {
        URL url = new URL("https://images.pexels.com/photos/212324/pexels-photo-212324.jpeg");
//        FileUtils.copyURLToFile(url, new File("\"C:\\\\Users\\\\yuanbaojian\\\\Desktop\\\\1.png\""));
        URLConnection conn = url.openConnection();
        File file = new File("C:\\Users\\yuanbaojian\\Desktop\\1.png");
        file.createNewFile();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
        conn.setRequestProperty("Authorization", pexelApiKey);
        conn.connect();
        FileUtils.copyInputStreamToFile(conn.getInputStream(), file);
//        picutreFile.createNewFile();
    }


}
