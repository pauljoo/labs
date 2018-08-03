package info.xpanda.labs.monitor.core;

import info.xpanda.labs.monitor.core.metric.MonitorMetric;
import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import info.xpanda.labs.monitor.core.transaction.TracerEventTypeEnum;
import info.xpanda.labs.monitor.core.transaction.TransactionHelper;
import io.opentracing.Span;

public class MonitorHeartbeat {
    public static void logHeartbeat(TracerHolder tracerHolder, String name, MonitorMetric metric) {
        Span span = tracerHolder.getTracer().buildSpan(name).start();
        TransactionHelper.logEvent(span, name, TracerEventTypeEnum.HEARTBEAT, metric);
        span.finish();
    }
}
