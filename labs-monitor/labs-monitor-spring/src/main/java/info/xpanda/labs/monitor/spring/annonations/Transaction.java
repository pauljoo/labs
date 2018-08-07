package info.xpanda.labs.monitor.spring.annonations;

import info.xpanda.labs.monitor.core.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.core.transaction.TracerTransactionTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Transaction {
    TracerTransactionTypeEnum type();

    String name();

    MetricTypeEnum metric() default MetricTypeEnum.NULL;

    String metricValue() default "";
}
