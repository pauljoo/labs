package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.metric.MonitorMetric;
import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.transaction.TracerEventTypeEnum;
import info.xpanda.labs.monitor.transaction.TracerTransactionBuilder;
import info.xpanda.labs.monitor.transaction.TracerTransactionTypeEnum;

public class MonitorHeatbeat {
    public static void logHeatbeat(TracerHolder tracerHolder, String name, MonitorMetric metric) {
        TracerTransactionBuilder.newFinshTransacion(tracerHolder, TracerTransactionTypeEnum.HEARTBEAT, name, TracerEventTypeEnum.HEARTBEAT, metric);
    }
}
