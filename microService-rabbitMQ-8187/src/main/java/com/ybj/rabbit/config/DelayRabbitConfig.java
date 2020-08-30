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

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wei.zhou
 * @Description:
 * @create 13:30 2020/3/23
 */
//@Configuration
/*延迟队列*/
public class DelayRabbitConfig {
	//队列 起名：test_delay_queue
	@Bean
	public Queue TestDelayQueue() {
		return new Queue("delay_queue", true);  //true 是否持久
	}

	//Delay交换机 起名：test_delay_exchange
	@Bean
    CustomExchange TestDelayExchange() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-delayed-type", "direct");
		return new CustomExchange("delay_exchange", "x-delayed-message", true, false, args);
	}

	//绑定  将队列和交换机绑定, 并设置用于匹配键：test_delay_routing
	@Bean
    Binding bindingDelay() {
		return BindingBuilder.bind(TestDelayQueue()).to(TestDelayExchange()).with("delay_routing").noargs();
	}
}
