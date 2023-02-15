package com.cienet.zheng.stock.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhengshan
 * @description
 * @create 2023/2/15
 */
@Configuration
@MapperScan(basePackages = "com.cienet.zheng.stock.dao")
public class TestDBConfiguration {

    @Value("${spring.datasource.url}")
    private String vipDatasourceUrl;
    @Value("${spring.datasource.username}")
    private String vipDatasourceUserName;
    @Value("${spring.datasource.password}")
    private String vipDatasourceUserPassport;
    @Value("${spring.datasource.driver-class-name}")
    private String vipDatasourceUserDriverClass;

    @Bean
    public DataSource vipDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(vipDatasourceUserDriverClass);
        dataSource.setUrl(vipDatasourceUrl);
        dataSource.setUsername(vipDatasourceUserName);
        dataSource.setPassword(vipDatasourceUserPassport);
        return dataSource;
    }
}
