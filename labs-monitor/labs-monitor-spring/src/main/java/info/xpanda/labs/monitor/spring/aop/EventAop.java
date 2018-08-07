package info.xpanda.labs.monitor.spring.aop;

import info.xpanda.labs.monitor.core.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.core.metric.MonitorMetric;
import info.xpanda.labs.monitor.core.transaction.EventHelper;
import info.xpanda.labs.monitor.spring.annonations.Event;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class EventAop {
    @Pointcut("@annotation(info.xpanda.labs.monitor.spring.annonations.Event)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        //获取传播属性
        Method method = ((MethodSignature)pjp.getSignature()).getMethod();
        Event event = method.getAnnotation(Event.class);
        if(event.metric() == MetricTypeEnum.NULL){
            EventHelper.logEvent(event.name(), event.type());
        }else{
            EventHelper.logEvent(event.name(), event.type(), new MonitorMetric(event.metric(), event.metricValue()));
        }
        Object result = pjp.proceed();
        return result;
    }
}
