package com.ybj.crawler.Learn.DesignPattern.template;

abstract class Coffee {
    // 模板方法，定义了冲泡咖啡的步骤
    public final void makeCoffee() {
        boilWater();
        addCoffeePowder();
        pourInCup();
        addCondiments();
    }

    // 加水
    public void boilWater() {
        System.out.println("Boiling water");
    }

    // 加咖啡粉
    public abstract void addCoffeePowder();

    // 倒入杯中
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    // 抽象方法 添加调料
    public abstract void addCondiments();
}

// 具体的咖啡类 - Espresso
class Espresso extends Coffee {
    public void addCoffeePowder() {
        System.out.println("Adding Espresso coffee powder");
    }

    public void addCondiments() {
        System.out.println("Adding sugar");
    }
}

// 具体的咖啡类 - Latte
class Latte extends Coffee {
    public void addCoffeePowder() {
        System.out.println("Adding Latte coffee powder");
    }

    public void addCondiments() {
        System.out.println("Adding milk");
    }
}

public class TemplatePatternDemo {
    public static void main(String[] args) {
        Coffee espresso = new Espresso();
        Coffee latte = new Latte();

        System.out.println("Making Espresso:");
        espresso.makeCoffee();

        System.out.println("\nMaking Latte:");
        latte.makeCoffee();
    }
}
