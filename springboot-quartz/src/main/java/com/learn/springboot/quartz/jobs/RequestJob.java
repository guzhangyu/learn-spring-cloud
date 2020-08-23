package com.learn.springboot.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author zhangyugu
 * @Date 2020/8/21 11:28 上午
 * @Version 1.0
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
@Service
@Slf4j
public class RequestJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        log.info("jobGroup={}, jobName={}, jobDesc={}, triggerGroup={}, triggerName={}, triggerDesc={}",
                jobDetail.getKey().getGroup(),
                jobDetail.getKey().getName(),
                jobDetail.getDescription(),
                trigger.getKey().getGroup(),
                trigger.getKey().getName(),
                trigger.getDescription());

        Object url = mergedJobDataMap.get("url");
        if(url!=null && !StringUtils.isEmpty(url.toString())) {
            log.info("url={}", url);
        }
    }
}
