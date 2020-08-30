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
package com.ybj.rabbit.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
* <p>有关rabbitMQ的配置</p>
 * @return
 * @author yuanbaojian
 * @date 2020/7/15
 * @time 21:14
 */
@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{

	@Autowired
	RabbitTemplate rabbitTemplate;

	@PostConstruct
	public void initMethod(){
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.setReturnCallback(this);
	}

	//消息发送到exchange中触发
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
		System.out.println("ConfirmCallback:     "+"确认情况："+ack);
		System.out.println("ConfirmCallback:     "+"原因："+cause);
		if(ack){
			System.out.println(" 消息发送成功 ");
		} else{
			System.out.println(" 消息发送失败 ");
		}
	}


	//失败后返回
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("ReturnCallback:     "+"消息："+message);
		System.out.println("ReturnCallback:     "+"回应码："+replyCode);
		System.out.println("ReturnCallback:     "+"回应信息："+replyText);
		System.out.println("ReturnCallback:     "+"交换机："+exchange);
		System.out.println("ReturnCallback:     "+"路由键："+routingKey);
	}
}
