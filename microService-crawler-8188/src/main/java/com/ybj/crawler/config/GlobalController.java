package com.ybj.crawler.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
public class GlobalController {
    @InitBinder
    public void handleException(WebDataBinder binder) {
        System.out.println("初始化请求");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Map handleException(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", ex.toString());
        log.error("-------------------------something goes wrong " + ex.toString());
        return map;
    }
}
