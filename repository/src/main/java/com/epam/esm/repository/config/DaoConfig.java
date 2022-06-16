package com.epam.esm.repository.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = {"com.epam.esm"})
@PropertySource(value = {"classpath:application.properties"})
public class DaoConfig {
    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig("/dbDev.properties");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Profile("dev")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    @Profile("prod")
    public DataSource dataSourceProd() {
        HikariConfig hikariConfig = new HikariConfig("/dbProd.properties");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Profile("prod")
    public JdbcTemplate jdbcTemplateProd() {
        return new JdbcTemplate(dataSourceProd());
    }
}
