package com.ybj.api.constant;

/**
 * 系统配置相关常量
 * @author caicai.gao
 *
 */
public class ConfigConstants {
    
    /****************************  系统常量      ************************************/

    /**登录请求的地址*/
    public static final String REQUEST_LOGIN = "/auth/login";

    /** 系统名称 */
    public static final String SYSTEMNAME = "CAPP";
    
    /** 管理员名称 */
    public static final String ADMIN_USER = "admin";
    
    /** 30分钟内密码最大错误次数 */
    public static final int ALLOW_FAIL_COUNT = 5;
    
    /** 密码过期时间 */
    public static final int LOCK_TIME = 30;
    
    /** 默认密码 */
    public static final String OAUTHTOKEN = "123456";
    
    /** 错误编码 */
    public static final int ERROR_CODE_NONE = 0;
    public static final int ERROR_CODE_SYSTEM = 10000;
    public static final int ERROR_CODE_BUSINESS = 10001;
    public static final int ERROR_CODE_USER = 10002;

    /** 虚拟目录 */
    public static final String REPOSITORY_PATH = "/RepositoryEntry";

    /** 产品目录 */
    public static final String PRODUCT_PATH = "Product";

    /** 工艺目录 */
    public static final String PROCESS_PATH = "Process";

    /** 资源目录 */
    public static final String RESOURCE_PATH = "Resource";

    /** 仿真文件目录 */
    public static final String SIMULATION_PATH = "Simulation";

    /** 各类工艺文目录 */
    public static final String WORK_INSTRUCTION_PATH = "WorkInstruction";

    /** 工艺节点相关附件目录 */
    public static final String ATTACHMENT_PATH = "Attachment";

    /** 装配工艺文件目录 */
    public static final String AO_PATH = "AO";

    /** 工序工步文件类目录 */
    public static final String OPERATION_PATH = "Operation";

    /** 二维文件目录 */
    public static final String TWO_DIMENSION_PATH = "2D";

    /** 三维文件目录 */
    public static final String THREE_DIMENSION_PATH = "3D";

    /** AR文件目录 */
    public static final String AR_PATH = "AR";

    /** 临时文件目录 */
    public static final String TEMP_PATH = "temp";

    /** 模板目录 */
    public static final String TEMPLATE = "Template";

    /** pdf模板目录 */
    public static final String PDF_TEMPLATE = "pdfTemplate";

    /** pdf模板的Data目录 */
    public static final String DATA = "Data";

    /** 接口文档路径 */
    public static final String API_DOC_PATH = "doc.html";

    /** start 接口文档所需资料路径 */
    public static final String WEBJARS = "webjars";

    public static final String SWAGGER = "swagger-resources";

    public static final String API_VERSION = "v2";
    /** end 接口文档所需资料路径 */

    /** 通用接口路径 */
    public static final String INTERFACE_PATH = "commonInterface";

    /** JSON文件后缀 */
    public static final String JSON_SUFFIX = ".json";

    /** ZIP文件后缀 */
    public static final String ZIP_SUFFIX = ".zip";

    /** HTML文件后缀 */
    public static final String HTML_SUFFIX = ".html";

    /** HttpResponse 的 contentType*/
    public static final String CONTENT_TYPE = "application/json";

    /** 日期格式 */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** 错误信息标识 */
    public static final String ERROR_MSG = "errmsg";

    /** 添加失败 */
    public static final short FAIL = 0;
    
    /**  添加成功 */
    public static final short SUCCESS = 1;
    
    /** 添加信息已存在 */
    public static final short EXIST = 2;
    
    /** 字符串null */
    public static final String NULL_STRING = "null";
    
    /** 字符串true */
    public static final String TRUE_STRING = "true";
    
    /** 字符串false */
    public static final String FALSE_STRING = "false";
    
    /** 双引号 */
    public static final String QUOTATION_MARKS_STRING = "\"";
    
    /** 升序 */
    public static final String ASC_STRING = "asc";
    
    /** linux操作系统 */
    public static final String OS_LINUX = "linux";
    
}
