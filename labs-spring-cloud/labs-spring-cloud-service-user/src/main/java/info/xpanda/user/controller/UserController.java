package info.xpanda.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        restTemplate.getForObject("http://test.jianghuiyao.info:25001/info",String.class);
        return "hello";
    }
}
