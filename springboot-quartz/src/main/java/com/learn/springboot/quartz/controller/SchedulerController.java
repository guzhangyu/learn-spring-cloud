package com.learn.springboot.quartz.controller;


import com.learn.springboot.quartz.entity.ResultCode;
import com.learn.springboot.quartz.entity.ResultData;
import com.learn.springboot.quartz.entity.SchedulerEntity;
import com.learn.springboot.quartz.service.SchedulerService;
import com.learn.springcloud.dao.AccountMapper;
import com.learn.springcloud.entity.Account;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 调度任务控制层.
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/9 16:45
 */
@RestController
public class SchedulerController {
    private static Logger logger = LoggerFactory.getLogger(SchedulerController.class);

    @Resource
    private SchedulerService schedulerService;

    /**
     * JdbcTemplate 默认已经被放在了容器中，直接使用即可.
     */
    @Resource(name = "quartzJdbcTemplate")
    private JdbcTemplate quartzJdbcTemplate;

    @Resource
    AccountMapper accountMapper;

    // 用于测试双数据源时，mybatis数据源使用是否正常
    @GetMapping("findAccount")
    public Account findAccount(@RequestParam("id") Long id) {
        return accountMapper.selectById(id);
    }

    /**
     * 查询注册成功的作业信息，从 qrtz_job_details、qrtz_triggers、qrtz_cron_triggers 进行关联查询
     * http://localhost:8080/schedule/findSchedulers?pageNum=1&pageSize=10
     * 注意：因为一个 job 可以对应多个 trigger，所以使用左连接.
     *
     * @param pageNum  ：查询的页码，默认为
     * @param pageSize ：查询的每页的条数，默认为 20
     * @return
     */
    @GetMapping("schedule/findSchedulers")
    public ResultData findSchedulers(Integer pageNum, Integer pageSize) {
        ResultData resultData = null;
        try {
            pageNum = pageNum == null ? 1 : pageNum <= 0 ? 1 : pageNum;
            pageSize = pageSize == null ? 1 : pageSize <= 0 ? 1 : pageSize;
            String sql = "select t4.*,t3.cron_expression from \n" +
                    "(select \n" +
                    "t1.sched_name,t1.job_name,t1.job_group,t1.description as job_desc,t1.job_class_name ,t1.job_data ,\n" +
                    "t2.trigger_name,t2.trigger_group,t2.trigger_state,t2.description as trigger_desc,t2.trigger_type , t2.job_data as trigger_data \n" +
                    "from qrtz_job_details t1 left outer join qrtz_triggers t2 \n" +
                    "on t1.sched_name=t2.sched_name and t1.job_name = t2.job_name and t1.job_group = t2.job_group\n" +
                    ") t4 left outer join qrtz_cron_triggers t3 on t3.sched_name = t4.sched_name and t3.trigger_name = t4.trigger_name and t3.trigger_group = t4.trigger_group " +
                    "limit " + ((pageNum - 1) * pageSize) + "," + (pageNum * pageSize);
            List<Map<String, Object>> queryForList = quartzJdbcTemplate.queryForList(sql);
            resultData = new ResultData(ResultCode.SUCCESS, queryForList);
        } catch (DataAccessException e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 注册作业。如果 Job 或者 Trigger 已经存在，则替换它们. 否则新增。有添加和修改的功能。
     * 如：第一次 job1 与 trigger1 关联注册，第二次再新增 job1 与 trigger2，则第二次是修改了 job1,同时新增了 trigger2，此时 job1 便关联了两个触发器，依次类推.
     * 再如：第一次 job1 与 trigger1 关联注册，第二次再新增 job2 与 trigger1，此时 job2 是新增，trigger1 会改为关联 job2，原来的 job1 此时没有关联触发器了.
     * http://localhost:8080/schedule/scheduleJob   使用 post 请求，body 正文参数如：
     * <p>
     * {
     * "job_name": "j1000",
     * "job_group": "reqJobGroup",
     * "job_class_name": "com.wmx.quartzjdbc.jobs.RequestJob",
     * "job_data": {
     * "url": "https://wangmaoxiong.blog.csdn.net/article/details/105057405"
     * },
     * "trigger_name": "t1000",
     * "trigger_group": "requestGroup",
     * "trigger_desc": "每30秒访问一次",
     * "cron_expression": "0/30 * * * * ?"
     * }
     *
     * @param schedulerEntity
     * @return
     */
    @PostMapping("schedule/scheduleJob")
    public ResultData scheduleJob(@RequestBody SchedulerEntity schedulerEntity) {
        ResultData resultData = null;
        try {
            //必须传入 cron_expression、job_class_name
            if (StringUtils.isEmpty(schedulerEntity.getCron_expression()) || StringUtils.isEmpty(schedulerEntity.getJob_class_name())) {
                resultData = new ResultData(ResultCode.PARAM_IS_BLANK, "cron_expression or job_class_name");
                return resultData;
            }
            schedulerService.scheduleJob(schedulerEntity);
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 重新注册任务的触发器，如果指定的触发器不存在，则不做更新.
     * http://localhost:8080/schedule/rescheduleJob
     * post 参数如：
     * {
     * "trigger_name": "1f593c5e-9100-4ef3-9a0c-9f2fa6fd278f",
     * "trigger_group": "requestGroup",
     * "trigger_desc": "每1分钟访问一次",
     * "cron_expression": "0 0/1 * * * ?",
     * "trigger_data": {
     * "url": "https://wangmaoxiong.blog.csdn.net/article/details/105309522"
     * }
     * }
     *
     * @param schedulerEntity
     * @return
     */
    @PostMapping("schedule/rescheduleJob")
    public ResultData rescheduleJob(@RequestBody SchedulerEntity schedulerEntity) {
        ResultData resultData = null;
        try {
            //必须传入 cron_expression、job_class_name
            if (StringUtils.isEmpty(schedulerEntity.getCron_expression())) {
                resultData = new ResultData(ResultCode.PARAM_IS_BLANK, "cron_expression");
                return resultData;
            }
            Date nextDate = schedulerService.rescheduleJob(schedulerEntity);
            resultData = new ResultData(ResultCode.SUCCESS, nextDate);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 注册 job 与 触发器。区别于上面的是这里会对 作业和触发器进行分开注册.
     * job_class_name 不能为空时，注册 JobDetail 作业详情，如果已经存在，则更新.
     * cron_expression 不为空时，注册触发器（注册触发器时，对应的作业必须先存在）：
     * <span>根据参数 job_name、job_group 获取 JobDetail，如果存在，则关联此触发器与 JobDetail，然后注册触发器，</span>
     *
     * @param schedulerEntity
     * @return
     */
    @PostMapping("schedule/scheduleJobOrTrigger")
    public ResultData scheduleJobOrTrigger(@RequestBody SchedulerEntity schedulerEntity) {
        ResultData resultData = null;
        try {
            schedulerService.scheduleJobOrTrigger(schedulerEntity);
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 删除指定的单个调度作业
     * http://localhost:8080/schedule/deleteJob?jobName=xx&jobGroup=
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @GetMapping("schedule/deleteJob")
    public ResultData deleteJob(@RequestParam String jobName, @RequestParam String jobGroup) {
        ResultData resultData = null;
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            schedulerService.deleteJob(jobKey);
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 删除指定的多个调度作业
     * http://localhost:8080/schedule/deleteJobList
     * post 请求参数举例：
     * [{
     * "name": "j1000",
     * "group": "reqJobGroup"
     * },
     * {
     * "name": "j2000",
     * "group": "reqJobGroup"
     * }
     * ]
     *
     * @param jobKeyList
     * @return
     */
    @PostMapping("schedule/deleteJobList")
    public ResultData deleteJobList(@RequestBody List<Map<String, String>> jobKeyList) {
        ResultData resultData = null;
        try {
            if (jobKeyList != null && jobKeyList.size() > 0) {
                List<JobKey> jobKeys = new LinkedList<>();
                for (Map map : jobKeyList) {
                    Object group = map.get("group");
                    Object name = map.get("name");
                    if (ObjectUtils.isEmpty(group) && !ObjectUtils.isEmpty(name)) {
                        jobKeys.add(JobKey.jobKey(String.valueOf(name), String.valueOf(group)));
                    }
                }
                if (jobKeys.size() > 0) {
                    schedulerService.deleteJobList(jobKeys);
                }
            }
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 清除/删除所有计划数据，包括所有的 Job，所有的 Trigger，所有的 日历。
     * clear 清除之后，仍然可以继续注册，然后作业可以继续被触发执行.
     * http://localhost:8080/schedule/clear
     *
     * @return
     */
    @GetMapping("schedule/clear")
    public ResultData clear() {
        ResultData resultData = null;
        try {
            schedulerService.clear();
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (SchedulerException e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 停止/关闭 quartz 调度程序，关闭了整个调度的线程池，意味者所有作业都不会继续执行。
     * 关闭后无法使用 start 重新启动，只能重启应用.
     * http://localhost:8080/schedule/shutdown
     * The Scheduler has been shutdown.
     *
     * @return
     */
    @GetMapping("schedule/shutdown")
    public ResultData shutdown() {
        ResultData resultData = null;
        try {
            schedulerService.shutdown();
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (SchedulerException e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 暂停指定作业
     * http://localhost:8080/schedule/pauseJob?jobName=xx&jobGroup=
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @GetMapping("schedule/pauseJob")
    public ResultData pauseJob(@RequestParam String jobName, @RequestParam String jobGroup) {
        ResultData resultData = null;
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            schedulerService.pauseJob(jobKey);
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 暂停所有作业.
     * http://localhost:8080/schedule/pauseAll
     *
     * @return
     */
    @GetMapping("schedule/pauseAll")
    public ResultData pauseAll() {
        ResultData resultData = null;
        try {
            schedulerService.pauseAll();
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 恢复指定作业继续运行.
     * http://localhost:8080/schedule/resumeJob?jobName=xx&jobGroup=
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @GetMapping("schedule/resumeJob")
    public ResultData resumeJob(@RequestParam String jobName, @RequestParam String jobGroup) {
        ResultData resultData = null;
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            schedulerService.resumeJob(jobKey);
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }

    /**
     * 恢复所有作业.
     * http://localhost:8080/schedule/resumeAll
     *
     * @return
     */
    @GetMapping("schedule/resumeAll")
    public ResultData resumeAll() {
        ResultData resultData = null;
        try {
            schedulerService.resumeAll();
            resultData = new ResultData(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            resultData = new ResultData(ResultCode.FAIL, null);
            logger.error(e.getMessage(), e);
        }
        return resultData;
    }
}
