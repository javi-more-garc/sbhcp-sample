package com.jmgits.sample.sbhcp.web.base.base.config;

import com.jmgits.sample.sbhcp.web.Application;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

/**
 * Created by javier on 08/01/17.
 */
@Configuration
@ComponentScan(basePackages = { //
        "com.jmgits.sample.sbhcp.web",//
}, excludeFilters = {
        @ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = Application.class)
})
@EnableAsync
public class DefaultWebConfig {
}
