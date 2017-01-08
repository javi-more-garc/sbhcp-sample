package com.jmgits.sample.sbhcp.web.base.base.config;

import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by javier on 08/01/17.
 */
@Profile(value = {"dev","pro"})
@Configuration
@ServiceScan
public class CloudConfig extends AbstractCloudConfig {

    @Bean
    public ApplicationInstanceInfo applicationInfo() {
        return cloud().getApplicationInstanceInfo();
    }
}
