package com.ybj.crawler.utils.FunnyMessage;

import com.ybj.crawler.utils.NetWorkUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 虹屁
 */
public class FunnyMessage {

    public static final String rainbowFartURL = "https://chp.shadiao.app/api.php";
    public static final String url = "https://nmsl.shadiao.app/api.php?level=min&lang=zh_cn";


    @Test
    public void getRainBowFart() throws IOException {
        Set sweet = new HashSet();
        int number = 200;
        for (int i = 0; i < number; i++) {
            String pageContent = NetWorkUtils.getPageContent(rainbowFartURL, "POST");
            sweet.add(pageContent);
        }
        Iterator sweetIterator = sweet.iterator();
        while (sweetIterator.hasNext()) {
            System.out.println(sweetIterator.next());
        }
    }

    @Test
    public void getLBW() throws IOException {
        Set sweet = new HashSet();
        int number = 100;
        for (int i = 0; i < number; i++) {
            String pageContent = NetWorkUtils.getPageContent(url,"POST");
            sweet.add(pageContent);
        }
        Iterator sweetIterator = sweet.iterator();
        while (sweetIterator.hasNext()) {
            System.out.println(sweetIterator.next());
        }
    }

}
