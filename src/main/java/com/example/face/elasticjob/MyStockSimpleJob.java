package com.example.face.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * Description: 使用注解定义job
 * User: zhangll
 * Date: 2019-12-09
 * Time: 10:44
 */
@Component
@ElasticScheduler(cron = "0/1 * * * * ?", shardingTotalCount = 4, name = "测试注解",
        shardingItemParameters = "0=0,1=0,2=1,3=1", jobParameters = "parameter")
public class MyStockSimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("------Thread ID: %s, 任务分片数: %s, " +
                        "当前分片项: %s.当前参数: %s," +
                        "当前任务名称: %s.当前任务参数: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()

        ));
    }
}
