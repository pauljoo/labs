package info.xpanda.labs.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * 访问认证
 */
public class AccessFilter extends ZuulFilter {
    private final static Set<String> WHITE_URL = new HashSet<>();

    protected ProxyRequestHelper proxyRequestHelper;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        if(WHITE_URL.contains(url)){
            return null;
        }
        if("/login".equals(request.getRequestURI())){
            //Login请求

            //登录成功，获取权限
        }

        if(!checkPermission()){

        }
        else{
            //验证权限

        }
        return null;
    }

    private boolean login(){
        return true;
    }

    private boolean checkPermission(){
        return true;
    }
}
