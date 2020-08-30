package com.ybj.crawler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.ybj.crawler.utils.Crawler.FIleDownload.t66y.startDownload;

/**
 * @Author FileController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {

    @GetMapping("t66y")
    public void get() throws IOException {
        String urlPrefix="http://www.budejie.com/";
        int pageNumber=100;
        for (int i = 10; i < pageNumber; i++) {
            String url=urlPrefix+i;
            log.info("URL为 {}", url);
            startDownload(url);
            System.out.println("第 "+i+" 页下载完成" );
        }
    }
}
