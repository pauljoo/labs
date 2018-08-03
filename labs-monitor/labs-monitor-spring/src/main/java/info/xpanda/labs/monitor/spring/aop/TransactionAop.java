package info.xpanda.labs.monitor.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TransactionAop {

    @Pointcut("@annotation(info.xpanda.labs.monitor.spring.annonations.Transaction)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        return pjp.proceed();
    }
}
