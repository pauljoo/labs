package info.xpanda.labs.spring.cloud.service.system.common.config;

import brave.Tracing;
import brave.context.slf4j.MDCCurrentTraceContext;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import brave.spring.webmvc.SpanCustomizingAsyncHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@Import({
        TracingClientHttpRequestInterceptor.class,
        SpanCustomizingAsyncHandlerInterceptor.class
})
public class ZipkinConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private SpanCustomizingAsyncHandlerInterceptor spanCustomizingAsyncHandlerInterceptor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired TracingClientHttpRequestInterceptor tracingClientHttpRequestInterceptor;

    @PostConstruct
    public void init() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(restTemplate.getInterceptors());
        interceptors.add(tracingClientHttpRequestInterceptor);
        restTemplate.setInterceptors(interceptors);
    }
    @Bean
    public Tracing brave(){
        return Tracing.newBuilder()
                .localServiceName("")
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(MDCCurrentTraceContext.create()) // puts trace IDs into logs
                .build();
    }

    @Override public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(spanCustomizingAsyncHandlerInterceptor);
    }
//    @Bean
//    public BraveServletFilter braveServletFilter(Brave brave){
//        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),brave.serverResponseInterceptor(),new DefaultSpanNameProvider());
//        return filter;
//    }
//
//    @Bean
//    public OkHttpClient okHttpClient(Brave brave){
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(brave.clientRequestInterceptor(), brave.clientResponseInterceptor(), new DefaultSpanNameProvider()))
//                .build();
//        return client;
//    }
}
