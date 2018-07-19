package info.xpanda.labs.spring.cloud.service.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {
    @Value("${xpanda.dev.default:}")
    private String devDefaultA;
    @Value("${xpanda.dev.common:}")
    private String devCommon;
    @Value("${xpanda.dev.dev:}")
    private String devDev;
    @Value("${xpanda.labs.system.default:}")
    private String defaultA;
    @Value("${xpanda.labs.system.common:}")
    private String common;
    @Value("${xpanda.labs.system.dev:}")
    private String dev;
    @Value("${xpanda.labs.system.test:}")
    private String test;

    @Value("${xpanda.labs.user.dev:}")
    private String devUser;

    @GetMapping("/info")
    public String info(HttpServletRequest request){
        log.info("I'm System Service!");
        log.info(devDefaultA);
        log.info(devCommon);
        log.info(devDev);
        log.info(defaultA);
        log.info(common);
        log.info(dev);
        log.info(test);
        log.info(devUser);
        return "I'm System Service!";
    }

    @GetMapping("/tcc")
    public String tcc(){
        return "I'm System: Try TCC!";
    }
    @GetMapping("/confirmTcc")
    public String confirmTcc(){
        return "I'm System: Confirm TCC!";
    }
    @GetMapping("/cancelTcc")
    public String cancelTcc(){
        return "I'm System: Cancel TCC!";
    }
}
