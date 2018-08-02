package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.transaction.TracerTransaction;
import info.xpanda.labs.monitor.transaction.TracerTransactionBuilder;
import info.xpanda.labs.monitor.transaction.TracerTransactionTypeEnum;

public class TracerUrl {
    private String urlName;

    private TracerTransaction tracerTransaction;

    public TracerUrl(TracerHolder tracerHolder, String urlName) {
        this.tracerTransaction = TracerTransactionBuilder.newTransacion(tracerHolder, TracerTransactionTypeEnum.URL.getName(), urlName);
        this.urlName = urlName;
    }

    public void finish(){
        this.tracerTransaction.finish();
    }

    public void result(boolean result){
        this.tracerTransaction.result(result);
    }
}
