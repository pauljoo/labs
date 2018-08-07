package info.xpanda.labs.monitor.spring.aop;

import info.xpanda.labs.monitor.core.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.core.metric.MonitorMetric;
import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import info.xpanda.labs.monitor.core.transaction.TransactionHelper;
import info.xpanda.labs.monitor.spring.annonations.Event;
import info.xpanda.labs.monitor.spring.annonations.Transaction;
import io.opentracing.Span;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
public class EventAop {
    @Resource
    private TracerHolder tracerHolder;

    @Pointcut("@annotation(info.xpanda.labs.monitor.spring.annonations.Event)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        //获取传播属性
        Method method = ((MethodSignature)pjp.getSignature()).getMethod();
        Event event = method.getAnnotation(Event.class);
        Span activeSpan = tracerHolder.getTracer().activeSpan();
        Span span = tracerHolder.getTracer().buildSpan(event.name()).asChildOf(activeSpan).start();
        if(event.metric() == MetricTypeEnum.NULL){
            TransactionHelper.logEvent(span ,event.type());
        }else{

            TransactionHelper.logEvent(span ,event.type(), new MonitorMetric(event.metric(), event.metricValue()));
        }


        Object result = pjp.proceed();
        span.finish();
        return result;
    }
}
