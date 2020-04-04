package com.example.face.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Description:定义job
 * User: zhangll
 * Date: 2019-11-25
 * Time: 10:43
 */

public class MyElasticJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(shardingContext.getJobName() + "执行:分片参数" + shardingContext.getShardingParameter() + "，当前分片项：" + shardingContext.getShardingItem());
    }
}
