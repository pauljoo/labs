package info.xpanda.user.controller;

import info.xpanda.user.remoting.SystemRemoting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private SystemRemoting systemRemoting;

    @RequestMapping("/info")
    @ResponseBody
    public String info(){
        String info = systemRemoting.info();
        log.info("System Say: " + info);
        return "I'm User Service!";
    }
}
