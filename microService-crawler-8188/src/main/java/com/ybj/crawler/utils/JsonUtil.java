package com.ybj.crawler.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.ybj.crawler.model.UserInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class JsonUtil {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/yuanbaojian/Downloads/shga_sample_750k/person_info.json");
        BufferedReader read = IoUtil.toBuffered(Files.newBufferedReader(file.toPath()));
        System.out.println("read = " + read);
        String content = null;
        List<UserInfo> userInfoList = new LinkedList<>();
        int i = 1;
        while ((content = read.readLine())!=null  && i< 100){
            try {
                UserInfo userInfo = JSONUtil.toBean(content, UserInfo.class);
                userInfoList.add(userInfo);
                i++;
            } catch (Exception e){
                System.out.println("e = " + e);
            }
            System.out.println(content);
        }
        System.out.println("i = " + i);

    }
}
