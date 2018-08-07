package info.xpanda.labs.monitor.spring.servlet;

import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import io.opentracing.Scope;
import io.opentracing.SpanContext;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapExtractAdapter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class TracerFilter extends OncePerRequestFilter{
    @Resource
    private TracerHolder tracerHolder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Map<String, String> headers = extractHeaders(request);

        SpanContext spanContext = tracerHolder.getTracer().extract(Format.Builtin.HTTP_HEADERS, new TextMapExtractAdapter(headers));
        try(Scope scope = tracerHolder.getTracer().buildSpan(request.getRequestURI().toString()).asChildOf(spanContext).startActive(true)){
            chain.doFilter(request, response);
        }
    }

    private Map<String, String> extractHeaders(HttpServletRequest request){
        Map<String, String> headers = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }

        return headers;
    }
}
