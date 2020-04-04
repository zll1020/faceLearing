package com.example.face.zookeeper;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.example.face.elasticjob.MyElasticJobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Description:
 * User: zhangll
 * Date: 2019-11-26
 * Time: 14:15
 */
@Configuration
@ConditionalOnExpression("'${regCenter.serverList}'.length() > 0")
public class MyZookeeperConfiguration {


    @Autowired
    private DataSource dataSource;

    /**
     * 配置zookeeper注册中心
     */
    @Bean(initMethod = "init")  // 需要配置init执行初始化逻辑
    public ZookeeperRegistryCenter regCenter(
            @Value("${regCenter.serverList}") final String serverList,
            @Value("${regCenter.namespace}") final String namespace) {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverList, namespace);
        zookeeperConfiguration.setMaxRetries(3); //设置重试次数,可设置其他属性
        zookeeperConfiguration.setSessionTimeoutMilliseconds(50000000); //设置会话超时时间,尽量大一点,否则项目无法正常启动
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

    /**
     * 配置任务监听器
     *
     * @return
     */
    @Bean
    public ElasticJobListener elasticJobListener() {
        return new MyElasticJobListener();
    }

    /**
     * 将作业运行的痕迹进行持久化到DB
     */
    @Bean
    public JobEventConfiguration jobEventConfiguration() {
        return new JobEventRdbConfiguration(dataSource);
    }

}
