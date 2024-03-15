package com.ybj.api.constant;

import lombok.Getter;

/**
 * 业务相关常量
 * @author caicai.gao
 *
 */
public class BusniessConstants {

    /** 分页总记录数*/
    public static final String TOTAL_COUNT = "total";


    public static final String version2 = "version2";

    /** 工艺数据编号流水号长度 */
    public static final int NUMBER_LENGTH = 8;

    /** 默认初始版本 */
    public static final String DEFAULT_VERSION = "A";

    /** 操作类型-复制 */
    public static final String OPERATION_COPY = "1";

    /** 操作类型-剪切 */
    public static final String OPERATION_CUT = "0";

    /** COPY节点后缀名 */
    public static final String COPY_SUFFIX = "_Copy";

    /**
     * 材料类型枚举类
     * @author caicai.gao
     *
     */
    @Getter
    public enum MaterialEnum{

        // 材料类型 （名称，值）
        MATERIAL("材料", "Material"), ASST("辅助件", "Asst");

        MaterialEnum(String name, String value) {
            this.name = name;
            this.value = value;
        }

        private final String name;

        private final String value;

    }

    /**
     * 冻结状态枚举类
     * @author caicai.gao
     *
     */
    @Getter
    public enum StatusEnum{

        // 冻结状态状态 （名称，值）
        NORMAL("正常", 1), FREEZE("冻结", 0);

        StatusEnum(String name, int value) {
            this.name = name;
            this.value = value;
        }

        private final String name;

        private final int value;

    }
    


}
