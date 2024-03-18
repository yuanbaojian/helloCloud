package com.ybj.crawler.Learn.DesignPattern.chainOfResponsibility;

// 客户端
public class Client {
    public static void main(String[] args) {
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();

        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        // h1发送请求， h2和h3都会处理
        h1.handleRequest(5);
        h1.handleRequest(15);
        h1.handleRequest(25);
    }
}