package info.xpanda.labs.spring.cloud.gateway;


import info.xpanda.cbb.spring.boot.zuul2.Zuul2StartEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(GatewayApplication.class);
        application.addListeners(new Zuul2StartEvent());
        application.run(args);
    }

    //access command line arguments
    @Override
    public void run(String... args) throws Exception {
        System.out.println("");
        //do something
    }
}