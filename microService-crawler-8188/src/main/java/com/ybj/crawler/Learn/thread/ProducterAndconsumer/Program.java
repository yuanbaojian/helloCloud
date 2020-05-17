package com.ybj.crawler.Learn.thread.ProducterAndconsumer;

public class Program {

    public static void main(String[] args) {
        ProductPool productPool=new ProductPool(15);

        new Productor(productPool).start();
        new Consumer(productPool).start();
    }

}
