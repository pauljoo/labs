package info.xpanda.labs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        testHome("world");
        return "Hello World!";
    }

    private void testHome(String prefix){
        System.out.println("hello");
    }
    @RequestMapping("/sample")
    @ResponseBody
    public String sample() {
        return "Hello World!";
    }
}
