package com.ybj.crawler.Learn.CleanCode;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class NullPointException {


    @Test
    public void testReturnNull() {
        List<Object> objects = Collections.emptyList();
        System.out.println("objects = " + objects);
    }
}
