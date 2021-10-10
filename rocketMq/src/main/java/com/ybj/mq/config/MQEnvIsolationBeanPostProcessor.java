package com.ybj.mq.config;

import com.ybj.mq.annotation.MQEnvIsolation;
import com.ybj.mq.constants.MQEnvIsolationConstants;
import com.ybj.mq.util.MQEnvIsolationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;

/**
 * MQ消息环境隔离配置
 *
 * @see <a href="https://blog.csdn.net/limeoracle/article/details/108584406?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-2&spm=1001.2101.3001.4242">参考资料</a>
 * @author Zhang Jian
 * @since 0.0.1
 */
@Slf4j
public class MQEnvIsolationBeanPostProcessor implements BeanPostProcessor {

    @SuppressWarnings("unchecked")
    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        MQEnvIsolation mqIsolateByEnv = bean.getClass().getAnnotation(MQEnvIsolation.class);
        if (Objects.isNull(mqIsolateByEnv)) {
            return bean;
        }

        try {
            RocketMQMessageListener targetAnnotation = bean.getClass().getAnnotation(RocketMQMessageListener.class);
            if (Objects.nonNull(targetAnnotation)) {
                // 获取代理处理器
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(targetAnnotation);

                // 获取私有 memberValues 属性
                Field memberValuesField = invocationHandler.getClass()
                        .getDeclaredField(MQEnvIsolationConstants.ANNOTATION_MEMBER_VALUES);
                memberValuesField.setAccessible(true);

                // 获取实例的属性map
                Map<String, Object> memberValuesValue = (Map<String, Object>) memberValuesField.get(invocationHandler);

                // 修改属性值
                SelectorType selectorType = targetAnnotation.selectorType();
                if (SelectorType.TAG == selectorType) {
                    String selectorExpression = targetAnnotation.selectorExpression();
                    // 如果选择器是默认值"*"则清空选择器
                    if (StringUtils.equals(selectorExpression,
                            MQEnvIsolationConstants.DEFAULT_ATTRIBUTE_SELECTOR_EXPRESSION)) {
                        selectorExpression = MQEnvIsolationConstants.EMPTY_STRING;
                    }
                    memberValuesValue.put(MQEnvIsolationConstants.ATTRIBUTE_SELECTOR_EXPRESSION,
                            MQEnvIsolationUtils.assembleAttribute(selectorExpression));
                    String consumerGroup = targetAnnotation.consumerGroup();
                    memberValuesValue.put(MQEnvIsolationConstants.ATTRIBUTE_CONSUMER_GROUP,
                            MQEnvIsolationUtils.assembleAttribute(consumerGroup));
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }

        return bean;
    }

}
