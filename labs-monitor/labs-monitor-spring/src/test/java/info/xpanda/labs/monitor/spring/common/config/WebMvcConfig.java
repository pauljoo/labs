package info.xpanda.labs.monitor.spring.common.config;

import info.xpanda.labs.monitor.spring.servlet.TracerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean tracerFilterRegistrationBean(){
        //委托Spring进行管理
        DelegatingFilterProxy filter = new DelegatingFilterProxy("tracerFilter");

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/index");
        return filterRegistrationBean;
    }

    @Bean
    public TracerFilter tracerFilter(){
        return new TracerFilter();
    }
}
