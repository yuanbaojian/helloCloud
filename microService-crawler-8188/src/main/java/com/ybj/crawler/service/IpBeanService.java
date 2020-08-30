package com.ybj.crawler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybj.crawler.model.IpBean;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ybj
 * @since 2020-02-10
 */
public interface IpBeanService extends IService<IpBean> {

    /**
     * 爬取web上的代理IP
     *
     * @return
     */
    List<IpBean> getIpFromWeb() throws IOException;

    /**
     * 异步获得有效ip，并发验证有效性
     *
     * @param
     * @return void
     * @author yuanbaojian
     * @date 2020/3/6
     * @time 9:22
     */
    Set<IpBean> getValidIpByAsyncMethod() throws InterruptedException;

    /**
     * 通过自定义线程获得有效ip
     *
     * @param
     * @return java.util.Set<com.ybj.crawler.model.IpBean>
     * @author yuanbaojian
     * @date 2020/3/6
     * @time 10:44
     */
    Set<IpBean> getValidIpByThread();

    public Future<String> executeAsyncTaskWithResult(String param);

    /**
    * <p></p>
     * @param ipBean
     * @return void
     * @author yuanbaojian
     * @date 2020/7/21
     * @time 13:54
     */
    void expandNumber(IpBean ipBean);

    String getIpFromFreeProxy() throws IOException, ExecutionException, InterruptedException;
}
