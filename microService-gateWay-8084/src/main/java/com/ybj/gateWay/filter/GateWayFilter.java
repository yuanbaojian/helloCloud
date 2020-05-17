package com.ybj.gateWay.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ybj.gateWay.constants.AnonUrlEnum;
import com.ybj.gateWay.constants.AuthConstants;
import com.ybj.gateWay.constants.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 网关过滤器
 * 往网关发送的请求
 *   1. yml中未匹配到的URL ---404
 *   2. yml中匹配到的URL&&对应模块未启动 ---503
 *   3. yml中匹配到额URL&&对应模块启动 ---进入filter
 * @author yuanbaojian
 * @date 2020/4/16
 * @time 14:23
 */
@Slf4j
@Component
public class GateWayFilter implements GlobalFilter,Ordered{

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String requestUri = request.getURI().getPath();
        ServerHttpRequest.Builder mutate = request.mutate();
        // 可以直接放行的URL
        for (AnonUrlEnum url : AnonUrlEnum.values()){
            if(requestUri.contains(url.getValue())){
                ServerHttpRequest build = mutate.build();
                return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
            }
        }
        try {
            String token= request.getHeaders().get("Authorization").get(0);
            String loginName = getLoginName(token);
            String redisKey = AuthConstants.TOKEN_PREFIX + loginName;
            String redisToken = stringRedisTemplate.opsForValue().get(redisKey);
            // token过期
            if(redisToken == null){
                return getVoidMono(serverWebExchange, JsonResult.tokenExpired("您的会话已失效请重新登录!"));
            } else{
                // 更新token时间
                stringRedisTemplate.expire(redisKey, 30L, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            // token为null
            return getVoidMono(serverWebExchange, JsonResult.tokenIsNull("请携带凭证信息进行资源请求!"));
        }
        return gatewayFilterChain.filter(serverWebExchange);
    }


    @Override
    public int getOrder() {
        return 0;
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

    /**
     * 网关直接向前台发送消息
     * 不进行服务转发
     *
     * @param body
     */
    @NotNull
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, JsonResult body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }


}
