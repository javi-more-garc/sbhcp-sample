package com.jmgits.sample.sbhcp.web;

import com.jmgits.sample.sbhcp.core.base.config.DefaultCoreConfig;
import com.jmgits.sample.sbhcp.web.base.base.config.DefaultWebConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Created by javi.more.garc on 08/01/17.
 */
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@Import(value = {DefaultCoreConfig.class, DefaultWebConfig.class})
public class TestApplication {

}
