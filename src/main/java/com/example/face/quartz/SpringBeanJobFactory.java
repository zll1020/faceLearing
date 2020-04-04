package com.example.face.quartz;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

/**
 * Description:
 * User: zhangll
 * Date: 2019-12-12
 * Time: 15:36
 */
public class SpringBeanJobFactory implements JobFactory {

    private String[] ignoredUnknownProperties;
    private SchedulerContext schedulerContext;

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {
        return null;
    }

    /*protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

        //从applicationContext 获取job对象
        Object job = (this.applicationContext != null ?
                this.applicationContext.getAutowireCapableBeanFactory().createBean(
                        bundle.getJobDetail().getJobClass(), AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR, false) :
                super.createJobInstance(bundle));

        // 属性注入
        if (isEligibleForPropertyPopulation(job)) {
            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(job);
            MutablePropertyValues pvs = new MutablePropertyValues();
            if (this.schedulerContext != null) {
                pvs.addPropertyValues(this.schedulerContext);
            }
            pvs.addPropertyValues(bundle.getJobDetail().getJobDataMap());
            pvs.addPropertyValues(bundle.getTrigger().getJobDataMap());
            if (this.ignoredUnknownProperties != null) {
                for (String propName : this.ignoredUnknownProperties) {
                    if (pvs.contains(propName) && !bw.isWritableProperty(propName)) {
                        pvs.removePropertyValue(propName);
                    }
                }
                bw.setPropertyValues(pvs);
            } else {
                bw.setPropertyValues(pvs, true);
            }
        }

        return job;
    }*/
}
