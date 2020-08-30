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

/**
 * @author: wei.zhou
 * @Description:
 * @create 17:09 2020/3/19
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
/*direct exchange(直连型交换机),*/
public class DirectRabbitConfig {

	//队列 起名：test_direct_queue
	@Bean
	public Queue TestDirectQueue() {
		return new Queue("test_direct_queue", true);  //true 是否持久
	}

	//Direct交换机 起名：test_direct_exchange
	@Bean
    DirectExchange TestDirectExchange() {
		return new DirectExchange("test_direct_exchange");
	}

	//绑定  将队列和交换机绑定, 并设置用于匹配键：test_direct_routing
	@Bean
    Binding bindingDirect() {
		return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("test_direct_routing");
	}
}