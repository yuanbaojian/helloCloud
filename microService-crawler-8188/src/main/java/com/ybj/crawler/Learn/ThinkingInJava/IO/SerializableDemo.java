package com.ybj.crawler.Learn.ThinkingInJava.IO;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.io.*;
import java.util.Arrays;

public class SerializableDemo {

    // public static void main(String[] args) {
    //     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    //     try (ObjectOutputStream outputStream = new ObjectOutputStream(buffer)){
    //         outputStream.writeObject(new User("zhangSan",14));
    //         System.out.println(Arrays.toString(buffer.toByteArray()));
    //     } catch (IOException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Cacheable()
    static class User implements Serializable {

        private static final long serialVersionUID = 2709425275741743918L;

        String name;

        Integer age;

        String address;

        String hello;
    }
}
