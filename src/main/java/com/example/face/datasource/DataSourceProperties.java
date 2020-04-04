package com.example.face.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Description:定义数据源
 * User: zhangll
 * Date: 2019-12-09
 * Time: 10:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource.jdbc")
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;

    @Bean
    @Primary
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
