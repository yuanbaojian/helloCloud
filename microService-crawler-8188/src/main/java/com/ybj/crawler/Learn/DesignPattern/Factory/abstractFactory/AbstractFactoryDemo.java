package com.ybj.crawler.Learn.DesignPattern.Factory.abstractFactory;

// 抽象产品接口
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

// 具体产品类
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Render a button in a Windows style");
    }
}

class MacOSButton implements Button {
    public void paint() {
        System.out.println("Render a button in a MacOS style");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Render a checkbox in a Windows style");
    }
}

class MacOSCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Render a checkbox in a MacOS style");
    }
}

// 抽象工厂接口
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 具体工厂类
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacOSFactory implements GUIFactory {
    public Button createButton() {
        return new MacOSButton();
    }
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}

// 客户端代码
public class AbstractFactoryDemo {
    private Button button;
    private Checkbox checkbox;

    public AbstractFactoryDemo(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

    public static void main(String[] args) {
        GUIFactory factory = new WindowsFactory();
        AbstractFactoryDemo app = new AbstractFactoryDemo(factory);
        app.paint();

        factory = new MacOSFactory();
        app = new AbstractFactoryDemo(factory);
        app.paint();
    }
}
