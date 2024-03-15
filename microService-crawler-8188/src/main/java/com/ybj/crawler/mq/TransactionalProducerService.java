//package com.ybj.crawler.mq;
//
//import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TransactionalProducerService {
//
//    @Autowired
//    RocketMQTemplate rocketMQTemplate;
//
//    @RocketMQTransactionListener
//    class TransactionListenerImpl implements RocketMQLocalTransactionListener {
//        @Override
//        public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
//            try {
//                // 在这里执行本地事务，根据本地事务的结果返回 COMMIT 或 ROLLBACK
//                // 如果成功，返回 COMMIT；如果失败，返回 ROLLBACK
//                return RocketMQLocalTransactionState.COMMIT;
//            } catch (Exception e) {
//                return RocketMQLocalTransactionState.ROLLBACK;
//            }
//        }
//
//        @Override
//        public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
//            // 在这里检查本地事务状态并返回 COMMIT 或 ROLLBACK
//            return RocketMQLocalTransactionState.COMMIT;
//        }
//    }
//
//    public void sendTransactionalMessage(String topic, String message) {
//        Message<String> msg = MessageBuilder.withPayload(message).build();
//        // 发送事务消息
//        // 在执行完本地事务后，RocketMQ 会根据事务监听器的反馈来决定是否提交或回滚消息
//        rocketMQTemplate.sendMessageInTransaction(topic, msg, null);
//    }
//}
