////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑          永无BUG         永不修改              //
////////////////////////////////////////////////////////////////////
package com.ybj.rabbit.controller;

import com.ybj.rabbit.config.Constants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: wei.zhou
 * @Description:
 * @create 17:11 2020/3/19
 */
@RestController
public class SendMessageController {
	@Autowired
	RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

	@GetMapping ("/sendDirectMessage")
	public String sendDirectMessage() {
		String messageId = String.valueOf(UUID.randomUUID());
		String messageData = "test message, hello!";
		Date createTime = new Date();
		Map<String, Object> map = new HashMap<>();
		map.put("createTime", createTime);
		map.put("messageId", messageId);
		map.put("messageData", messageData);

		CorrelationData correlationData = new CorrelationData(messageId);

		//将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
		rabbitTemplate.convertAndSend("test_direct_exchange", "test_direct_routing", map, correlationData);

		//延迟消息
//		rabbitTemplate.convertAndSend("delay_exchange", "delay_routing", map, message -> {
//			message.getMessageProperties().setHeader("x-delay", 5000);
//			return message;
//		}, correlationData);
		return "ok";

	}

	@GetMapping ("/sendTopicMessage1")
	public String sendTopicMessage1() {
		String messageId = String.valueOf(UUID.randomUUID());
		String messageData = "message: email ";
		String createTime = LocalDateTime.now().toString();
		Map<String, Object> emailMap = new HashMap<>();
		emailMap.put("messageId", messageId);
		emailMap.put("messageData", messageData);
		emailMap.put("createTime", createTime);
		rabbitTemplate.convertAndSend(Constants.topicExchange, Constants.topicRedPacketKey, emailMap);
		return "ok";
	}

	@GetMapping ("/sendTopicMessage2")
	public String sendTopicMessage2() {
		String messageId = String.valueOf(UUID.randomUUID());
		String messageData = "message: phonel ";
		String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String, Object> phonenMap = new HashMap<>();
		phonenMap.put("messageId", messageId);
		phonenMap.put("messageData", messageData);
		phonenMap.put("createTime", createTime);
		rabbitTemplate.convertAndSend("topic_exchange", "topic.phone", phonenMap);
		return "ok";
	}

	@GetMapping ("/sendFanoutMessage")
	public String sendFanoutMessage() {
		String messageId = String.valueOf(UUID.randomUUID());
		String messageData = "message: testFanoutMessage ";
		String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("messageData", messageData);
		map.put("createTime", createTime);
		rabbitTemplate.convertAndSend("fanoutExchange", null, map);
		return "ok";
	}


	/**
	 * 写个测试接口，把消息推送到名为‘non-existent-exchange’的交换机上（这个交换机是没有创建没有配置的
	 *
	 * @return
	 */
	@GetMapping ("/TestMessageAck")
	public String TestMessageAck() {
		String messageId = String.valueOf(UUID.randomUUID());
		String messageData = "message: non-existent-exchange test message ";
		String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("messageData", messageData);
		map.put("createTime", createTime);
		rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
		return "ok";
	}

	@GetMapping ("/TestMessageAck2")
	public String TestMessageAck2() {
		String messageId = String.valueOf(UUID.randomUUID());
		String messageData = "message: lonelyDirectExchange test message ";
		String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("messageData", messageData);
		map.put("createTime", createTime);
		rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
		return "ok";
	}
}
