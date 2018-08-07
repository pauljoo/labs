package info.xpanda.labs.monitor.spring.annonations;

import info.xpanda.labs.monitor.core.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.core.transaction.TracerEventTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Event {
    TracerEventTypeEnum type();

    String name();

    MetricTypeEnum metric();

    String metricValue();
}
