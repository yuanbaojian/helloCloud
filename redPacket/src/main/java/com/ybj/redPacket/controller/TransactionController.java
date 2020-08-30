package com.ybj.redPacket.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ybj.api.annotation.GetParamLog;
import com.ybj.api.model.JsonResult;
import com.ybj.redPacket.config.TopicRabbitConfig;
import com.ybj.redPacket.feign.UserServiceFeign;
import com.ybj.redPacket.model.Message;
import com.ybj.redPacket.model.RedPacket;
import com.ybj.redPacket.model.User;
import com.ybj.redPacket.service.MessageServiceI;
import com.ybj.redPacket.service.RedPacketServiceI;
import com.ybj.redPacket.service.UserRedPacketServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author TransactionController
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@RestController
@RequestMapping("/transaction")
@Api(value = "事务接口",tags = {"进行本地事务，分布式事务的测试"})
public class TransactionController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedPacketServiceI redPacketService;

    @Autowired
    UserServiceFeign userServiceFeign;

    @Autowired
    UserRedPacketServiceI userRedPacketService;

    @Autowired
    MessageServiceI messageService;

    @Autowired
    RedisTemplate redisTemplate;


    @Transactional
    @GetMapping("/insertByTransaction")
    @ApiOperation(value = "使用本地事务进行测试",notes = "针对单个数据源")
    public JsonResult getNumber(){
        RedPacket redPacket = new RedPacket();
        redPacket.setStock(1000);
        RedPacket redPacket1 = new RedPacket();
        redPacket1.setStock(10001);
        boolean result1 = redPacketService.save(redPacket);
        log.info("保存result1 {}", result1);
        System.out.println("redPacket1 = " + 1/0);
        boolean result2 = redPacketService.save(redPacket1);
        log.info("保存result2 {}", result2);
        return JsonResult.ok("操作成功完成");
    }

    /**
    * <p></p>
     * @param
     * @return java.util.List<com.ybj.user.model.User>
     * @author yuanbaojian
     * @date 2020/7/13
     * @time 18:29
     */
    @GetMapping("getUsers")
    public List<User> getUsers(){
        List<User> allUsers = userServiceFeign.getAllUsers();
        return allUsers;
    }


    @Transactional
    @ApiOperation(value = "使用分布式事务进行测试",notes = "红包库存减少，用户余额增加")
    @GetMapping("/grapRedPacket")
    public JsonResult grapRedPacket(String redPacketId, String userId) throws Exception {
        int result = userRedPacketService.grapRedPacketByPessimisticLock(redPacketId, userId);
        this.rabbitTemplate.convertAndSend("directs","redPacket","用户抢红包结果"+result);
        String uuId = UUID.randomUUID().toString();
        if(result == 0){
            log.info("{} 用户抢红包失败",userId);
            return  JsonResult.fail("抢红包失败");
        } else{
            log.info("{} 用户抢红包成功",userId);
            JsonResult jsonResult = userServiceFeign.addUserBalance(userId);
            log.info("{} 用户余额提升结果{}",userId,jsonResult.get("success"));
            if((Boolean)jsonResult.get("success")){
                return JsonResult.ok("抢红包成功");
            } else{
                // throw new Exception();
                return JsonResult.fail("抢红包失败");
            }
        }
    }
    @Transactional
    @ApiOperation(value = "使用RabbitMq解决分布式事务进行测试",notes = "红包库存减少，用户余额增加")
    @GetMapping("/grapRedPacketByRabbitMQ")
    public JsonResult grapRedPacketByRabbitMQ(String redPacketId, String userId) throws Exception {
        int result = userRedPacketService.grapRedPacketByPessimisticLock(redPacketId, userId);
        String uuId = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put(uuId, "redPacketId", redPacketId);
        redisTemplate.opsForHash().put(uuId, "userId", userId);
        if(result == 0){
            log.info("{} 用户抢红包失败",userId);
            return  JsonResult.fail("抢红包失败");
        } else{
            log.info("{} 用户抢红包成功",userId);
            // JsonResult jsonResult = userServiceFeign.addUserBalance(userId);
            // log.info("{} 用户余额提升结果{}",userId,jsonResult.get("success"));
            Message message = new Message();
            message.setId(uuId);
            //0表示消息未处理
            message.setStatus(0);
            message.setCreatedTime(LocalDateTime.now());
            message.setMessage(uuId + userId + " 用户抢到了红包, 准备提额");
            messageService.save(message);
            redisTemplate.opsForHash().put(uuId, "message", message);

            CorrelationData correlationData = new CorrelationData(uuId);
            Map<String,String> map = new HashMap<>();
            map.put("userId", userId);
            map.put("uuId", uuId);
            redisTemplate.opsForHash().put(uuId, "map", map);
            //发送消息
            this.rabbitTemplate.convertAndSend(TopicRabbitConfig.redPacketExchange, TopicRabbitConfig.routingKey,map);
            //发生异常，导致本地事务回滚。 需要采取
            int i = 3 / 0;
            return JsonResult.ok("抢红包成功, 准备提额");
        }

    }


}
