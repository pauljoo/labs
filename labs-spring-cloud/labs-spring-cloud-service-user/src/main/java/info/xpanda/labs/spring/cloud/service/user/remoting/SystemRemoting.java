package info.xpanda.labs.spring.cloud.service.user.remoting;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "labs-spring-cloud-service-system", fallback = SystemRemotingHystrix.class)
@RequestMapping("/system")
public interface SystemRemoting {
    @GetMapping("/info")
    String info();

    @GetMapping("/tcc")
    String tcc();

    @GetMapping("/confirmTcc")
    String confirmTcc();

    @GetMapping("/cancelTcc")
    String cancelTcc();
}
