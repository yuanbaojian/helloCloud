package com.ybj.crawler.Learn.DesignPattern.Factory.factoryMethod;

// 产品接口
interface Product {
    void operate();
}

// 具体产品
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

// 抽象工厂
abstract class Factory {
    abstract Product factoryMethod();
}

// 具体工厂
class ConcreteFactoryA extends Factory {
    Product factoryMethod() {
        return new ConcreteProductA();
    }
}

class ConcreteFactoryB extends Factory {
    Product factoryMethod() {
        return new ConcreteProductB();
    }
}

// 使用
public class FactoryMethodDemo {
    public static void main(String[] args) {
        Factory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.factoryMethod();
        productA.operate();
        
        Factory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.factoryMethod();
        productB.operate();
    }
}
