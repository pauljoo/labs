package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.transaction.TracerTransactionTypeEnum;
import info.xpanda.labs.monitor.transaction.TransactionHelper;
import io.opentracing.Span;

public class MonitorTask {
    private Span span;

    public MonitorTask(TracerHolder tracerHolder, String taskName) {
        span = tracerHolder.getTracer().buildSpan(taskName).start();
        TransactionHelper.newTransaction(span, TracerTransactionTypeEnum.TASK);
    }

    public void finish(){
        span.finish();
    }

    public void error(boolean error){
        TransactionHelper.logError(span, error);
    }
}
