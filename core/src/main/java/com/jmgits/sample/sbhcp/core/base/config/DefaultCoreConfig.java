package com.jmgits.sample.sbhcp.core.base.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by javi.more.garc on 08/01/17.
 */
@Configuration
@ComponentScan(basePackages = { //
        "com.jmgits.sample.sbhcp.model",//
        "com.jmgits.sample.sbhcp.core",//
})
@EnableJpaRepositories(basePackages = { //
        "com.jmgits.sample.sbhcp.core", //
})
@EntityScan(basePackages = { //
        "com.jmgits.sample.sbhcp.model", //
})
public class DefaultCoreConfig {
}
