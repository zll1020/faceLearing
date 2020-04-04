package com.example.face.quartz;

import lombok.Data;
import org.quartz.*;

import java.text.MessageFormat;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Description:
 * User: zhangll
 * Date: 2019-12-12
 * Time: 10:25
 */
@Data
public class MyJob implements Job {

    private int count;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        String format = MessageFormat.format(" ======== 当前线程 {3} 定时任务 {0},定时任务描述 {1},定时任务参数：{2},当前对象哈希值 {4} ======== ",
                jobDetail.getKey(), jobDetail.getDescription(), dataMap.toString(), Thread.currentThread().getName(), this.hashCode());
        System.out.println(format);
        System.out.println(this.count == dataMap.getInt("count"));
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //创建ScheduleFactory并获取Schedule
    private static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory("quartz.properties");
        return schedFact.getScheduler();
    }

    //创建 JobDetail 和 Trigger
    private static JobDetail getJobDetail() {
        JobDetail jobData = JobBuilder.newJob(MyJob.class)
                .withDescription("MyJob")
                .withIdentity("jobData", "hello")
                .usingJobData("JobName", "hello Job")
                .usingJobData("count", 1)
                .build();
        return jobData;
    }

    // 创建Trigger
    private static Trigger getTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                // 分组名称，trigger名称
                .withIdentity("myTrigger1", "hello")
                // 从现在开始
                .startNow()
                .usingJobData("JobName", "hello Trigger")
                // 调度触发方式
                .withSchedule(simpleSchedule()
                        // 启动后 5000 执行
                        .withIntervalInMilliseconds(5000L)
                        // 重复执行
                        .repeatForever())
                .build();
        return trigger;
    }

    //注册并启动
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = getScheduler();
        JobDetail jobDetail = getJobDetail();
        Trigger trigger = getTrigger();

        // 注册
        scheduler.scheduleJob(jobDetail, trigger);
        //启动
        scheduler.start();
    }
}
