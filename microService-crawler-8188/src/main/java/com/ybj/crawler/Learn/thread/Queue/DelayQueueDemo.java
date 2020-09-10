package com.ybj.crawler.Learn.thread.Queue;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
* <h2>延迟消息队列</h2>
* <p>用于测试订单过期测试
 * 必须实现Delayed接口
 * </p>
 * @author yuanbaojian
 * @date 2020/9/10
 */
@Data
public class DelayQueueDemo implements Delayed {

    /**
     * 订单号
     * */
    private String orderCode;

    /**
     * 过期时间
     */
    private Date expirationTime;



    public static void main(String[] args) {
        DelayQueue<DelayQueueDemo> queue = new DelayQueue<>();
        DelayQueueDemo o1 = new DelayQueueDemo();
        //第一个订单，过期时间设置为一分钟后
        o1.setOrderCode("1001");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        o1.setExpirationTime(calendar.getTime());
        DelayQueueDemo o2 = new DelayQueueDemo();
        //第二个订单，过期时间设置为现在
        o2.setOrderCode("1002");
        o2.setExpirationTime(new Date());
        //往队列中放入数据
        queue.offer(o1);
        queue.offer(o2);
        // 延时队列
        while (true) {
            try {
                DelayQueueDemo take = queue.take();
                System.out.println("订单编号：" + take.getOrderCode() + " 过期时间：" + take.getExpirationTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * <p>获得延期时间，
     * 只有剩余时间为0时， 元素才能被取出</p>
     * @author yuanbaojian
     * @date 2020/9/10
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    /**
    * <h2>排序比较函数</h2>
    * <p>通过这个函数，确定队列中元素的顺序</p>
     * @author yuanbaojian
     * @date 2020/9/10
     */
    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
