package info.xpanda.labs.monitor.spring.common.config;

import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfig {
    @Bean(initMethod = "init")
    public TracerHolder tracerHolder(){
        return new TracerHolder("monitor-spring");
    }
}
