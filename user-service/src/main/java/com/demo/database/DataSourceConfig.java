package com.demo.database;

import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final Dotenv dotEnv = Dotenv.configure().filename(".env").load();
    private final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private final String DRIVER = "postgresql";
    private String RDS_HOSTNAME = dotEnv.get("RDS_USER_HOSTNAME");
    private String RDS_PORT = dotEnv.get("RDS_USER_PORT");
    private String RDS_DBNAME = dotEnv.get("RDS_USER_DBNAME");
    private final String JDBC_URL = "jdbc:" + DRIVER + "://" + RDS_HOSTNAME + ":" + RDS_PORT + "/" + RDS_DBNAME;
    private String RDS_USERNAME = dotEnv.get("RDS_USER_USERNAME");
    private String RDS_PASSWORD = dotEnv.get("RDS_USER_PASSWORD");

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
