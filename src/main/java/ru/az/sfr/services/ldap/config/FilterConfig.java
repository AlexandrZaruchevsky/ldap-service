package ru.az.sfr.services.ldap.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import ru.az.sfr.services.ldap.filters.RequestLoggerFilter;
import ru.az.sfr.services.ldap.services.LogService;

import java.util.Collections;

@Configuration
public class FilterConfig {


    private final LogService logService;

    public FilterConfig(LogService logService) {
        this.logService = logService;
    }

    public FilterRegistrationBean<RequestLoggerFilter> loggerFilter(){
        FilterRegistrationBean<RequestLoggerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggerFilter(logService));
        registrationBean.setUrlPatterns(Collections.singleton("/api/*"));
//        registrationBean.addUrlPatterns();
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
