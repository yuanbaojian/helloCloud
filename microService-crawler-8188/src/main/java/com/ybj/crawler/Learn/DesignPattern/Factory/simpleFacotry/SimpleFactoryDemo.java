package com.ybj.crawler.Learn.DesignPattern.Factory.simpleFacotry;


//  产品接口
interface Product {
    void operate();
}

// 具体产品实现类
class ConcreteProductA implements Product {
    public void operate() {
        System.out.println("Product A operation.");
    }
}

class ConcreteProductB implements Product {
    public void operate() {
        System.out.println("Product B operation.");
    }
}

// 工厂类，用于实例化产品
class SimpleFactory {
    static Product createProduct(String type) {
        switch (type) {
            case "A":
                return new ConcreteProductA();
            case "B":
                return new ConcreteProductB();
            default:
                throw new IllegalArgumentException("Unknown product type.");
        }
    }
}

// 使用
public class SimpleFactoryDemo {
    public static void main(String[] args) {
        Product productA = SimpleFactory.createProduct("A");
        productA.operate();
        Product productB = SimpleFactory.createProduct("B");
        productB.operate();
    }
}
