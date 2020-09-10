package com.ybj.spring.model;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
* <p>
 *     消息队列测试
* </p>
 * @author yuanbaojian
 * @date 2020/9/10
 */
@Data
public class OrderDelayDto implements Delayed {

    /**
     * 订单号
     * */
    private String orderCode;

    /**
     * 过期时间
     */
    private Date expirationTime;

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expirationTime.getTime() - System.currentTimeMillis(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        OrderDelayDto orderDelayDto = (OrderDelayDto) o;
        long time = orderDelayDto.getExpirationTime().getTime();
        long time1 = this.getExpirationTime().getTime();
        return time == time1 ? 0 : time < time1 ? 1 : -1;
    }

    public static void main(String[] args) {
        DelayQueue<OrderDelayDto> queue = new DelayQueue<>();
        OrderDelayDto o1 = new OrderDelayDto();
        //第一个订单，过期时间设置为一分钟后
        o1.setOrderCode("1001");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        o1.setExpirationTime(calendar.getTime());
        OrderDelayDto o2 = new OrderDelayDto();
        //第二个订单，过期时间设置为现在
        o2.setOrderCode("1002");
        o2.setExpirationTime(new Date());
        //往队列中放入数据
        queue.offer(o1);
        queue.offer(o2);
        // 延时队列
        while (true) {
            try {
                OrderDelayDto take = queue.take();
                System.out.println("订单编号：" + take.getOrderCode() + " 过期时间：" + take.getExpirationTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
