package com.ybj.crawler.Learn.thread.JUC;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * @Author CountDownLatchDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class CountDownLatchDemo {

    enum CountryEnum{
        ONE(1,"韩"),
        TWO(2,"赵"),
        THREE(3,"魏"),
        FOUR(4,"齐"),
        FIVE(5,"楚"),
        ;

        @Getter
        private Integer retCode;
        @Getter
        private String retMessage;

        CountryEnum(Integer retCode, String retMessage) {
            this.retCode = retCode;
            this.retMessage = retMessage;
        }

        public static CountryEnum foreach( int index){
            CountryEnum[] values = CountryEnum.values();
            for (CountryEnum value : values) {
                if(index == value.getRetCode())
                {
                    return value;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
    }
}
