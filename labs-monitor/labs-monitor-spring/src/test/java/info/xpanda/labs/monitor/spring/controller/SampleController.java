package info.xpanda.labs.monitor.spring.controller;

import info.xpanda.labs.monitor.spring.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SampleController {
    @Resource
    private SampleService sampleService;

    @RequestMapping(value = {"", "/", "/index"})
    @ResponseBody
    public String sample(){
        sampleService.trade();
        return "test";
    }
}
