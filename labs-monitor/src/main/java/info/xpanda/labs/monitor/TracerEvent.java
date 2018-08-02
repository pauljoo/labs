package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.transaction.TracerTransactionBuilder;
import info.xpanda.labs.monitor.transaction.TracerTransactionTypeEnum;

public class TracerEvent {
    public static void logEvent(TracerHolder tracerHolder, String name) {
        TracerTransactionBuilder.newFinshTransacion(tracerHolder, TracerTransactionTypeEnum.EVENT.getName(), name);
    }
}
