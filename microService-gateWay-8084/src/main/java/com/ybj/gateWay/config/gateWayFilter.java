package com.ybj.gateWay.config;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ybj.api.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @Author gateWayFilter
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@Component
public class gateWayFilter implements GlobalFilter,Ordered{

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private static final String GATE_WAY_PREFIX = "/api";

    @Override
    public Mono<Void> filter(
            ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String requestUri = request.getURI().getPath();
        ServerHttpRequest.Builder mutate = request.mutate();
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            ServerHttpRequest build = mutate.build();
            return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
        }
        try {
            //此处可能npe错误
            String token= request.getHeaders().get("Authorization").get(0);
            String loginName = getLoginName(token);
            String redisKey = "LoginToken_" + loginName;
            String redisToken = stringRedisTemplate.opsForValue().get(redisKey);
            if(redisToken == null){
                return getVoidMono(serverWebExchange, JsonResult.fail("toen失效"));
            } else{
                stringRedisTemplate.expire(redisKey, 30L, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            log.error("用户Token过期异常", e);
            return getVoidMono(serverWebExchange, JsonResult.fail("token已过期"));
        }
        return gatewayFilterChain.filter(serverWebExchange);

    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
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
     * 网关抛异常
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
