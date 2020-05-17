package com.ybj.crawler.Quartz;//package com.ybj.crawler.Quartz;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import java.time.LocalDateTime;
//
//public class TestQuartz extends QuartzJobBean {
//    /**
//     * 执行定时任务
//     * @param jobExecutionContext
//     * @throws JobExecutionException
//     */
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//    System.out.println("quartz task " + LocalDateTime.now().toString());
//    }
//
//
//}