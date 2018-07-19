package info.xpanda.labs.spring.cloud.service.user.controller;

import info.xpanda.labs.spring.cloud.service.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    @ResponseBody
    public String info(){
        userService.info();
        return "I'm User Service!";
    }

    @GetMapping("/tcc")
    @ResponseBody
    public String tcc(){
        userService.tcc(1L, "22");
        return "I'm User: Try TCC!";
    }
}
