package com.example.face.quartz;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.util.ReflectionUtils;

/**
 * Description:
 * User: zhangll
 * Date: 2019-12-12
 * Time: 15:33
 */
public class AdaptableJobFactory implements JobFactory {
    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {
        try {
            Object jobObject = createJobInstance(triggerFiredBundle);
            return adaptJob(jobObject);
        } catch (Throwable ex) {
            throw new SchedulerException("Job instantiation failed", ex);
        }
    }

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Class<?> jobClass = bundle.getJobDetail().getJobClass();
        return ReflectionUtils.accessibleConstructor(jobClass).newInstance();
    }

    protected Job adaptJob(Object jobObject) throws Exception {
        if (jobObject instanceof Job) {
            return (Job) jobObject;
        } else if (jobObject instanceof Runnable) {
            //return new DelegatingJob((Runnable) jobObject);
            return null;
        } else {
            throw new IllegalArgumentException(
                    "Unable to execute job class [" + jobObject.getClass().getName() +
                            "]: only [org.quartz.Job] and [java.lang.Runnable] supported.");
        }
    }

}
