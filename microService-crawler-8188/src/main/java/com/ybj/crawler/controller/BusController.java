package com.ybj.crawler.controller;


import com.ybj.api.constant.BusniessConstants;
import com.ybj.api.model.JsonResult;
import com.ybj.crawler.common.Constants;
import com.ybj.crawler.model.BusInfo;
import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.utils.Crawler.IPCrawler.IPCrawler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.ybj.crawler.utils.Crawler.FIleDownload.GetBusInfo.HttpURLConnection_GET;

/**
 * @Author BusController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Api(value = "公交到站接口")
@RestController
@RequestMapping("/bus")
public class BusController {

    @RequestMapping(value = "/getAllIp", method = RequestMethod.POST)
    public List<IpBean> getAllIp() throws IOException {
        String version2 = BusniessConstants.version2;
        List<IpBean> IpBeanList = null;
        IpBeanList = IPCrawler.getIpList(Constants.HTTPS_URL, 1);
        return IpBeanList;
    }


    @ApiOperation(value = "获得759信息")
    @GetMapping(value = "/busInfo")
    public JsonResult getBusInfo(String param1) throws Exception {
        String path = "https://shanghaicity.openservice.kankanews.com/public/bus/Getstop?stoptype=0&stopid=10.&sid=7019f275eae92b302744ade1ac88763a";
        BusInfo busInfo = HttpURLConnection_GET(path);
        return JsonResult.ok().addData(busInfo);
    }

}
