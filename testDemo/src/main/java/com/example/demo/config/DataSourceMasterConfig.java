package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.properties.DataSourceMasterProperties;
import com.example.demo.properties.DruidProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by huanglijun on 2017/9/14.
 */
@Configuration
@EnableConfigurationProperties({DataSourceMasterProperties.class,DruidProperties.class})
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DataSourceMasterConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactoryMaster")
public class DataSourceMasterConfig {

    // 精确到 dataSource 目录
    static final String PACKAGE = "com.example.demo.dao.master";
    static final String MASTER_LOCATION = "classpath:mapper/master/*.xml";

    @Autowired
    private DataSourceMasterProperties dataSourceMasterProperties;
    @Autowired
    private DruidProperties druidProperties;

    @Bean(name = "dataSourceMaster")
    @Primary
    public DataSource dataSourceMaster() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceMasterProperties.getDriverClassName());
        dataSource.setUrl(dataSourceMasterProperties.getUrl());
        dataSource.setUsername(dataSourceMasterProperties.getUsername());
        dataSource.setPassword(dataSourceMasterProperties.getPassword());


        dataSource.setInitialSize(druidProperties.getInitialSize());
        dataSource.setMinIdle(druidProperties.getMinIdle());
        dataSource.setMaxActive(druidProperties.getMaxActive());
        dataSource.setMaxWait(druidProperties.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidProperties.getValidationQuery());
        dataSource.setTestWhileIdle(druidProperties.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(druidProperties.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dataSource.setFilters(druidProperties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties(druidProperties.getConnectionProperties());
        dataSource.setUseGlobalDataSourceStat(druidProperties.isUseGlobalDataSourceStat());

        return dataSource;
    }

    @Bean(name = "transactionManagerMaster")
    @Primary
    public DataSourceTransactionManager transactionManagerMaster() {
        return new DataSourceTransactionManager(dataSourceMaster());
    }

    @Bean(name = "sqlSessionFactoryMaster")
    @Primary
    public SqlSessionFactory sqlSessionFactoryMaster(@Qualifier("dataSourceMaster") DataSource dataSourceMaster) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceMaster);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceMasterConfig.MASTER_LOCATION));
        return sessionFactory.getObject();
    }
}
