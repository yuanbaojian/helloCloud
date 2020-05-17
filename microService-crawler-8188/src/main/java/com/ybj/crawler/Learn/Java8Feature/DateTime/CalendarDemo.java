package com.ybj.crawler.Learn.Java8Feature.DateTime;

import org.testng.annotations.Test;

import java.util.Calendar;

public class CalendarDemo {

    @Test
    public void test(){
        Calendar calendar=Calendar.getInstance();
        System.out.println("calendar = " + calendar);
    }
}
