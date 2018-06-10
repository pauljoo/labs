package info.xpanda.labs.spring.cloud.service.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class SystemController {
    @GetMapping("/info")
    public String info(HttpServletRequest request){
        log.info("I'm System Service!");
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
