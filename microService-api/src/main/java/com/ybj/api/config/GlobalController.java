package com.ybj.api.config;

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


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Map handleException(Exception ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 400);
        map.put("msg", ex.toString());
        log.error("Something goes wrong, Please read the log detail carefully " + ex.toString());
        return map;
    }
}
