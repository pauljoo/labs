package info.xpanda.system.controller;

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
}
