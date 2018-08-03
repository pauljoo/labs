package info.xpanda.labs.monitor.core;

import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import info.xpanda.labs.monitor.core.transaction.TracerTransactionTypeEnum;
import info.xpanda.labs.monitor.core.transaction.TransactionHelper;
import io.opentracing.Span;

public class MonitorUrl {
    private Span span;

    public MonitorUrl(TracerHolder tracerHolder, String taskName) {
        span = tracerHolder.getTracer().buildSpan(taskName).start();
        TransactionHelper.newTransaction(span, TracerTransactionTypeEnum.URL);
    }

    public void finish(){
        span.finish();
    }

    public void error(boolean error){
        TransactionHelper.logError(span, error);
    }
}
