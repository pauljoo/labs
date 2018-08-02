package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.metric.MonitorMetric;
import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.transaction.TracerEventTypeEnum;
import info.xpanda.labs.monitor.transaction.TracerTransactionBuilder;
import info.xpanda.labs.monitor.transaction.TracerTransactionTypeEnum;

public class MonitorEvent {
    public static void logEvent(TracerHolder tracerHolder, String name) {
        TracerTransactionBuilder.newFinshTransacion(tracerHolder, TracerTransactionTypeEnum.EVENT, name, TracerEventTypeEnum.EVENT);
    }

    public static void logEvent(TracerHolder tracerHolder, String name, MonitorMetric metric) {
        TracerTransactionBuilder.newFinshTransacion(tracerHolder, TracerTransactionTypeEnum.EVENT, name, TracerEventTypeEnum.EVENT, metric);
    }
}
