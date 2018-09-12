package info.xpanda.labs.license.service.impl;

import info.xpanda.labs.license.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "empty service";
    }
}
