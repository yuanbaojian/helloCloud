package com.ybj.crawler.Learn.ThinkingInJava.Ch19_Enum.replaceIf;

/**
 * 泛型类
 **/
public enum OperatorEnum {
    //  声明泛型
    ADD{
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    },MULTIPLY {
        @Override
        public int apply(int a, int b) {
            return a * b;
        }
    },SUBTRACT {
        @Override
        public int apply(int a, int b) {
            return a - b;
        }
    },DIVIDE {
        @Override
        public int apply(int a, int b) {
            return a / b;
        }
    };

    public abstract int apply(int a, int b);
}
