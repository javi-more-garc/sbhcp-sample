package com.jmgits.sample.sbhcp.web.base.base.config;

import org.springframework.boot.actuate.health.DataSourceHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Created by javier on 08/01/17.
 */
@Profile(value = {"dev","pro"})
@Configuration
public class HealthConfig {

    @Bean
    public HealthIndicator dbHealthIndicator(DataSource dataSource) {
        DataSourceHealthIndicator indicator = new DataSourceHealthIndicator(dataSource);
        indicator.setQuery("SELECT * FROM DUMMY");
        return indicator;
    }
}
