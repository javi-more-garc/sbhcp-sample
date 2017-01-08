package com.jmgits.sample.sbhcp.web.base.base.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

/**
 * Created by javi.more.garc on 03/10/16.
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket publicApi(@Value("${application.swagger.contact.name}") String name,
                            @Value("${application.swagger.contact.email}") String email,
                            @Value("${application.swagger.contact.url}") String url) {

        return new Docket(SWAGGER_2)
                .groupName("_publicApi")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api/v.*/public/.*"))
                .build()
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .globalResponseMessage(RequestMethod.PATCH, getPathResponseMessages())
                .apiInfo(apiInfo("Spring Boot HCP Sample API (pub)", "Spring Boot HCP Sample API (pub)", name, email, url))
                ;
    }

    @Bean
    public Docket userApi(@Value("${application.swagger.contact.name}") String name,
                          @Value("${application.swagger.contact.email}") String email,
                          @Value("${application.swagger.contact.url}") String url) {

        return new Docket(SWAGGER_2)
                .groupName("userApi")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api/v.*/user/.*"))
                .build()
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .globalResponseMessage(RequestMethod.PATCH, getPathResponseMessages())
                .apiInfo(apiInfo("Spring Boot HCP Sample API (user)", "Spring Boot HCP Sample API (user)", name, email, url))
                ;
    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(null);
    }

    //
    //  private methods

    private ApiInfo apiInfo(String title, String description, String name, String email, String url) {

        return new ApiInfoBuilder().title(title)
                .description(description)
                .contact(new Contact(name, url, email))
                .version("1.0.0")
                .build();
    }

    private List<ResponseMessage> getPathResponseMessages() {

        // the default values contain a 204

        return asList(
                new ResponseMessage(401, "Unauthorized", null, emptyMap(), Collections.emptyList()),
                new ResponseMessage(403, "Forbidden", null, emptyMap(), Collections.emptyList()),
                new ResponseMessage(404, "Not Found", null, emptyMap(), Collections.emptyList())
        );
    }
}
