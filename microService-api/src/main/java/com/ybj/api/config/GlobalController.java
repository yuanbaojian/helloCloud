package com.ybj.api.config;

import com.ybj.api.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author GlobalController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalController {


    /**
    * <p>返回一个MAP对象</p>
     * @return java.util.Map
     * @author yuanbaojian
     * @date 2020/7/15
     * @time 13:10
     */
    // @ExceptionHandler(Exception.class)
    // @ResponseStatus(HttpStatus.OK)
    // public Map<String, Object> handleException(Exception ex) {
    //     Map<String, Object> map = new HashMap<String, Object>();
    //     map.put("code", 400);
    //     map.put("msg", ex.toString());
    //     log.error("发生了异常: {}", ex.toString());
    //     log.error("异常发生位置: {}", ex.getStackTrace()[0]);
    //     return map;
    // }
}
