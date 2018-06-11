package info.xpanda.labs.oauth2.authorization.common.config;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用信息服务
 */
public class MyClientDetailsService implements ClientDetailsService {

    private final static Map<String, MyClientDetails> clientDetails = new HashMap<String, MyClientDetails>();

    @Override
    public ClientDetails loadClientByClientId(String applyName) throws ClientRegistrationException {
        MyClientDetails myClientDetails = clientDetails.get(applyName);

        if(myClientDetails == null) {
            throw new ClientRegistrationException("应用" + applyName + "不存在!");
        }

        return myClientDetails;
    }
}