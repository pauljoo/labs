package info.xpanda.labs.monitor.spring.aop;

import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import info.xpanda.labs.monitor.core.transaction.TransactionHelper;
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
public class TransactionAop {
    @Resource
    private TracerHolder tracerHolder;

    @Pointcut("@annotation(info.xpanda.labs.monitor.spring.annonations.Transaction)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        //获取传播属性
        Method method = ((MethodSignature)pjp.getSignature()).getMethod();
        Transaction transaction = method.getAnnotation(Transaction.class);
        Span activeSpan = tracerHolder.getTracer().activeSpan();
        Span span = tracerHolder.getTracer().buildSpan(transaction.type().getName() + ":" + transaction.name()).asChildOf(activeSpan).start();
        TransactionHelper.logTransaction(span, transaction.type());
        Object result = pjp.proceed();
        span.finish();
        return result;
    }
}
