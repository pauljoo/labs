package info.xpanda.user.service.impl;

import info.xpanda.cbb.transaction.core.Compensable;
import info.xpanda.user.remoting.SystemRemoting;
import info.xpanda.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SystemRemoting systemRemoting;
    @Override
    public String info() {
        String info = systemRemoting.info();
        log.info("System Say: " + info);
        return info;
    }

    @Override
    @Compensable
    public String tcc(Long id, String name) {
        return systemRemoting.tcc();
    }

    @Override
    public String confirmTcc() {
        return systemRemoting.confirmTcc();
    }

    @Override
    public String cancelTcc() {
        return systemRemoting.cancelTcc();
    }
}
