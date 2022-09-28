package com.demo.database;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Value("${rds.hostname}")
    private  String RDS_HOSTNAME;
    @Value("${rds.port}")
    private String RDS_PORT ;
    @Value("${rds.dbname}")
    private String RDS_DBNAME;
    @Value("${rds.username}")
    private String RDS_USERNAME ;
    @Value("${rds.password}")
    private String RDS_PASSWORD  ;
    private final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private final String DRIVER = "postgresql";
    private final String JDBC_URL = "jdbc:" + DRIVER + "://" + RDS_HOSTNAME + ":" + RDS_PORT + "/" + RDS_DBNAME;

    @Primary
    @Bean("dataSource")
    public DataSource getDataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMinimumIdle(10);
        dataSource.setMaximumPoolSize(30);
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setJdbcUrl(JDBC_URL);
        dataSource.setUsername(RDS_USERNAME);
        dataSource.setPassword(RDS_PASSWORD);
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
