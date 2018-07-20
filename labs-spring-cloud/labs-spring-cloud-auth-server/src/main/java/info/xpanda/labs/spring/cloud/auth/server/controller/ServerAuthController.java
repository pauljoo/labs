package info.xpanda.labs.spring.cloud.auth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务间鉴权接口
 *
 * 后续可以加入appkey，通过appid(1) + appkey(n)实现单个应用的多个权限的控制
 */
@RequestMapping("/auth/server")
@Controller
public class ServerAuthController {

    /**
     *
     * 获取应用的token,包含应用的基本信息
     * ClientId
     *
     * @param appId
     * @param sendTime 发送时间
     * @param signType
     * @param sign
     * @return
     */
    @PostMapping("/token")
    public String token(String appId, Long sendTime, String signType, String sign){
        //验证用户权限
        //TODO HMAC(APPID, sendTime, signType, secret) == sign

        //私钥 JWT(ClientId)
        return "";
    }


    /**
     *
     * 获取用于token解析用的公钥
     *
     * @return
     */
    @PostMapping("/token/pubkey")
    public String pubkey(){
        return "";
    }
    /**
     *
     * 获取允许调用该服务的白名单
     *
     * @param appId
     * @param sendTime
     * @param signType
     * @param sign
     * @return
     */
    @PostMapping("/acl/white")
    public String white(String appId, Long sendTime, String signType, String sign){
        //验证用户权限
        //TODO HMAC(APPID, sendTime, signType, secret) == sign
        return "";
    }
}
