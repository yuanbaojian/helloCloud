package com.ybj.deptProvider.controller;

import com.ybj.api.model.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author testController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@RestController
public class testController {

    @GetMapping("testGet")
    public JsonResult testGet(){
        return JsonResult.ok();
    }
}
