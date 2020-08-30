package com.ybj.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TopicRabbitConfig {

	//创建queue队列
	@Bean
	public Queue queue_one() {
		return new Queue(Constants.topicQueue);
	}

	//创建exchange交换机
	@Bean
    TopicExchange exchange() {
		return new TopicExchange(Constants.topicExchange);
	}


	/** We also provide a BindingBuilder to facilitate a “fluent API” style, as the following example shows: */
	@Bean
    Binding bindingExchangeMessage() {
		return BindingBuilder.bind(queue_one()).to(exchange()).with(Constants.topicRedPacketKey);
	}

}
