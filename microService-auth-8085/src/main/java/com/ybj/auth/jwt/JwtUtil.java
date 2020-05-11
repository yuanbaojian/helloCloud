package com.ybj.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author yabo.wang
 * @Date 2020/1/8 10:34
 * @Description 实现对用户名密码的加密处理，校验token是否正确
 */
@Slf4j
public class JwtUtil {

    /**令牌过期时间默认两个小时*/
    private static final long EXPIRE_TIME = 2 * 60 * 1000L;

    /**
     * @param password 密码
     * @Description 根据用户名密码以及失效时间生成token
     * @author wangyabo 
     * @date 2020/1/8 10:54
     */
    public static String generateToken(String loginName,String userId,String password){
        Algorithm algorithm = Algorithm.HMAC256(password);
        return JWT.create()
                .withClaim("loginName", loginName)
                .withClaim("userId", userId)
                .sign(algorithm);
    }

    /**
     * @param token token
     * @param userName 用户名
     * @param password 密码
     * @Description 验证token是否正确
     * @author wangyabo
     * @date 2020/1/9 10:50
     */
    static boolean verify(String token, String userName, String password){
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName",userName).build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("验证的结果:{}", jwt.getToken());
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            log.trace(e.getMessage());
            return false;
        }
    }


    public static String getLoginName(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            log.error(e.getMessage());
            return null;
        }
    }


}
