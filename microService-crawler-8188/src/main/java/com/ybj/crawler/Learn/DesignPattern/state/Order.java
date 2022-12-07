package com.ybj.crawler.Learn.DesignPattern.state;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    private int id;
    private OrderStatus status;
 

 
    @Override
    public String toString() {
        return "订单号：" + id + ", 订单状态：" + status;
    }
}