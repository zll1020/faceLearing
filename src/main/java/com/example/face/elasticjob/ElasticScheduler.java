package com.example.face.elasticjob;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:添加ElasticScheduler注解
 * User: zhangll
 * Date: 2019-12-09
 * Time: 10:34
 */
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticScheduler {
    /**
     * 任务名称
     *
     * @return
     */
    String name();

    /**
     * cron表达式，用于控制作业触发时间
     *
     * @return
     */
    String cron() default "";

    /**
     * 分片参数
     *
     * @return
     */
    String shardingItemParameters() default "";

    /**
     * 总分片数
     *
     * @return
     */
    int shardingTotalCount();

    /**
     * 任务描述信息
     *
     * @return
     */
    String description() default "";

    /**
     * 任务参数
     */
    String jobParameters() default "";
}
