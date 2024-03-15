package com.ybj.crawler.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisExample {
    public static void main(String[] args) {
        // Redis 主节点地址和端口
        String masterHost = "slpay_redis_fs_test2_001";
        int masterPort = 6379;

        // Redis 从节点地址和端口
        String slaveHost = "shopline-fs-test-group001-sentinel.inshopline.com:20026,shopline-fs-test-group002-sentinel.inshopline.com:20026,shopline-fs-test-group003-sentinel.inshopline.com:20026";
        int slavePort = 6379;

        // 需要查询的 key
        String key = "yourKey";

        // 连接主节点
        try (Jedis masterJedis = new Jedis(masterHost, masterPort)) {
            // 查询并打印 key 的值
            String value = masterJedis.get(key);
            System.out.println("Value from master: " + value);
        } catch (JedisConnectionException e) {
            System.err.println("Error connecting to the master: " + e.getMessage());
        }

        // 连接从节点
        try (Jedis slaveJedis = new Jedis(slaveHost, slavePort)) {
            // 查询并打印 key 的值
            String value = slaveJedis.get(key);
            System.out.println("Value from slave: " + value);
        } catch (JedisConnectionException e) {
            System.err.println("Error connecting to the slave: " + e.getMessage());
        }
    }
}
