package info.xpanda.labs.spring.cloud.zuul.common.config;

import info.xpanda.labs.spring.cloud.zuul.filter.AccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
