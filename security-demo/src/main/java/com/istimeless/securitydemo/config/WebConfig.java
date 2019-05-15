package com.istimeless.securitydemo.config;

import com.istimeless.securitydemo.filter.TimeFilter;
import com.istimeless.securitydemo.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiayin
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    
    @Resource
    private TimeInterceptor timeInterceptor;
    
    @Bean
    public FilterRegistrationBean<TimeFilter> timeFilter(){
        FilterRegistrationBean<TimeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }
}
