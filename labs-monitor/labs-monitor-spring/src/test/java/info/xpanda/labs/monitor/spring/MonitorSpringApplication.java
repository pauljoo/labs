package info.xpanda.labs.monitor.spring;

import info.xpanda.labs.monitor.spring.aop.EventAop;
import info.xpanda.labs.monitor.spring.aop.TransactionAop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonitorSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorSpringApplication.class, args);
    }

    @Bean
    public TransactionAop transactionAop(){
        return new TransactionAop();
    }
    @Bean
    public EventAop eventAop(){
        return new EventAop();
    }
}
