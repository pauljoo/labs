package info.xpanda.user.remoting;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "labs-spring-cloud-service-system", fallback = SystemRemotingHystrix.class)
public interface SystemRemoting {
    @GetMapping("/info")
    String info();
}
