package info.xpanda.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
    @RequestMapping("/info")
    public String info(){
        return "info";
    }
}
