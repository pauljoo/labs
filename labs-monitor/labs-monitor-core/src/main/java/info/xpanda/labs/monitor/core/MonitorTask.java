package info.xpanda.labs.monitor.core;

import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import info.xpanda.labs.monitor.core.transaction.TracerTransactionTypeEnum;
import info.xpanda.labs.monitor.core.transaction.TransactionHelper;
import io.opentracing.Span;

public class MonitorTask {
    private Span span;

    public MonitorTask(TracerHolder tracerHolder, String taskName) {
        span = tracerHolder.getTracer().buildSpan(taskName).start();
        TransactionHelper.logTransaction(span, TracerTransactionTypeEnum.TASK);
    }

    public void finish(){
        span.finish();
    }

    public void error(boolean error){
        TransactionHelper.logError(span, error);
    }
}
