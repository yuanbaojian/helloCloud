package com.ybj.gateWay.constants;

/**
 * @Author yabo.wang
 * @Date 2020/1/10 9:40
 * @Description 认证授权有关的常量
 */
public class AuthConstants {

    /**账号不存在*/
    public static final int ACCOUNT_EMPTY = 10001;

    /**密码不正确*/
    public static final int PASSWORD_ERROR = 10002;

    /**账号被禁用*/
    public static final int ACCOUNT_FORBIDDEN = 0;

    /**登录认证成功*/
    public static final int IDENTIFY_SUCCESS = 1111;

    /** toke过期时间 */
    public static final Integer TOKEN_EXPIRED_TIME = 2*60*60;

    /** token前缀 */
    public static final String TOKEN_PREFIX = "LoginToken_";

    /**
     * token已过期
     */
    public static final int TOKEN_EXPIRED = 1004;
}
