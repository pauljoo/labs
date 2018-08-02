package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.metric.MonitorMetric;
import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.transaction.TracerEventTypeEnum;
import info.xpanda.labs.monitor.transaction.TransactionHelper;
import io.opentracing.Span;

public class MonitorHeartbeat {
    public static void logHeartbeat(TracerHolder tracerHolder, String name, MonitorMetric metric) {
        Span span = tracerHolder.getTracer().buildSpan(name).start();
        TransactionHelper.logEvent(span, name, TracerEventTypeEnum.HEARTBEAT, metric);
        span.finish();
    }
}
