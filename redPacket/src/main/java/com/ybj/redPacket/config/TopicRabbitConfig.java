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

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.BinaryOperator;

/**
 * @author: wei.zhou
 * @Description:
 * @create 18:41 2020/3/19
 */
@Configuration
/*主题交换机*/
public class TopicRabbitConfig {

	//补偿队列与key
	public final static String routingCompensationKey = "topic.redPacketCompensation";
	public final static String redPacketcompensationQueue = "redPacketCompensationQueue";

	public final static String routingKey = "topic.redPacket";
	public final static String redPacketQueue = "redPacketQueue";

	public final static String redPacketExchange = "redPacket_exchange";

	@Bean
	public Queue queue_one() {
		return new Queue(TopicRabbitConfig.redPacketQueue);
	}

	//补偿队列
	@Bean
	public Queue compensationQueue() {
		return new Queue(TopicRabbitConfig.redPacketcompensationQueue);
	}



	@Bean
    TopicExchange exchange() {
		return new TopicExchange(redPacketExchange);
	}


	/** We also provide a BindingBuilder to facilitate a “fluent API” style, as the following example shows: */
	@Bean
    Binding bindingExchangeMessage() {
		BindingBuilder.bind(compensationQueue()).to(exchange()).with(TopicRabbitConfig.routingCompensationKey);
		return BindingBuilder.bind(queue_one()).to(exchange()).with(TopicRabbitConfig.routingKey);
	}


}
