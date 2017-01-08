package com.jmgits.sample.sbhcp.web;

import com.jmgits.sample.sbhcp.core.base.config.DefaultCoreConfig;
import com.jmgits.sample.sbhcp.web.base.base.config.DefaultWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Created by javi.more.garc on 08/01/17
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Import(value = {DefaultCoreConfig.class, DefaultWebConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
