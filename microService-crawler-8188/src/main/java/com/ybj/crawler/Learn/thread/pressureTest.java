package com.ybj.crawler.Learn.thread;

import com.ybj.crawler.utils.NetWorkUtils;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author pressureTest
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class pressureTest {


    public static void main(String[] args) throws IOException {
        final int i=0;
        ExecutorService service=Executors.newFixedThreadPool(1000);
        for(int j = 0; j < 100000; j++) {
            service.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    String pageContent = NetWorkUtils.getPageContent("https://api.26xf90zp4w.ncucoder.com/register", "POST");
                    System.out.println( LocalDateTime.now()+  "  " + pageContent);
                }
            });
//            String pageContent = NetWorkUtils.getPageContent("http://192.168.6.43:8889/CBTSystem/auth/login", "POST");
//            System.out.println( LocalDateTime.now()+  "  " + pageContent);
        }



    }
}
