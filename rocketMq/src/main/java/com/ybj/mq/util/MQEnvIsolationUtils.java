package com.ybj.mq.util;

import com.ybj.mq.constants.MQEnvIsolationConstants;
import org.apache.commons.lang3.StringUtils;

public class MQEnvIsolationUtils {

    private static String env;

    public static void setEnv(String env) {
        MQEnvIsolationUtils.env = env;
    }

    /**
     * 拼装环境信息
     *
     * @param rawValue 原始值
     * @return 拼装了环境变量后的属性值
     */
    public static String assembleAttribute(String rawValue) {
        if (StringUtils.isEmpty(env)) {
            return rawValue;
        }

        return StringUtils.isEmpty(rawValue) ? env : (env + MQEnvIsolationConstants.ENV_ATTRIBUTE_SEPARATOR + rawValue);
    }

    /**
     * 拼装消息Destination
     *
     * @param topic 主题
     * @return 消息Destination
     */
    public static String assembleDestination(String topic) {
        return assembleDestination(topic, null);
    }

    /**
     * 拼装消息Destination
     *
     * @param topic 主题
     * @param rawTag 原始标签
     * @return 消息Destination
     */
    public static String assembleDestination(String topic, String rawTag) {
        return topic + MQEnvIsolationConstants.TOPIC_TAGS_SEPARATOR + assembleAttribute(rawTag);
    }

}
