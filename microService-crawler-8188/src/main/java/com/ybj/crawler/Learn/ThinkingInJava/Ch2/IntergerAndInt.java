package com.ybj.crawler.Learn.ThinkingInJava.Ch2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @Owner yuanbaojian
 * @Team
 */
@Slf4j
public class IntergerAndInt {

    public static void main(String[] args) {
        Integer i = 2;
    }

    @Test
    public void equal(){
        int i = 100;
        Integer j = new Integer(100);
        Integer k = 100;
        // true
        log.info("i ==j {}" ,i == j);
        // true
        log.info("j.eqauls(i) {}", j.equals(i));
        //false
        log.info("j ==k {}", j==k);
        //false
        log.info("j eqauls k {}" , j.equals(k));
    }

    @Test
    public void equals2(){
        Integer i = 127;
        Integer j = 127;
        Integer k = 128;
        Integer l = 129;
        //true
        log.info("i ==j {}", i==j);
        //false
        log.info("k ==l {}", k==l);

        Integer.valueOf(100);
    }
}
