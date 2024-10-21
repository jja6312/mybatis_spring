package com.ssafy.mvc.config;

import com.ssafy.mvc.filter.AuthenticationFilter;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.addUrlPatterns("/board/*");
        return filterRegistrationBean;
    }
}
