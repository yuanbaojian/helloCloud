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
package com.ybj.redPacket.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* <p>有关rabbitMQ的配置</p>
 * @return
 * @author yuanbaojian
 * @date 2020/7/15
 * @time 21:14
 */
@Configuration
public class RabbitConfig {
	@Bean
	public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setMandatory(true);

		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {



			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				String correlationDataId = correlationData.getId();
				System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
				System.out.println("ConfirmCallback:     "+"确认情况："+ack);
				System.out.println("ConfirmCallback:     "+"原因："+cause);
				if(ack){
					System.out.println(" 消息发送成功 ");
				} else{
					System.out.println(" 消息发送失败, 准备重发");
					reSendMessage(correlationDataId);
				}
			}

			private void reSendMessage(String orderId) {
				String msg = "";
				Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_BYTES)
						.setContentEncoding("utf-8").setMessageId(orderId).build();
				CorrelationData correlationData = new CorrelationData(orderId);
				rabbitTemplate.convertAndSend(TopicRabbitConfig.redPacketExchange,TopicRabbitConfig.routingKey,message,correlationData);
			}
		});




		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				System.out.println("ReturnCallback:     "+"消息："+message);
				System.out.println("ReturnCallback:     "+"回应码："+replyCode);
				System.out.println("ReturnCallback:     "+"回应信息："+replyText);
				System.out.println("ReturnCallback:     "+"交换机："+exchange);
				System.out.println("ReturnCallback:     "+"路由键："+routingKey);
			}
		});

		return rabbitTemplate;
	}




}
