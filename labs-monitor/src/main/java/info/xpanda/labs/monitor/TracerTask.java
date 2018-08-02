package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.transaction.TracerTransaction;
import info.xpanda.labs.monitor.transaction.TracerTransactionBuilder;
import info.xpanda.labs.monitor.transaction.TracerTransactionTypeEnum;

public class TracerTask {
    private String taskName;

    private TracerTransaction tracerTransaction;

    public TracerTask(TracerHolder tracerHolder, String taskName) {
        this.tracerTransaction = TracerTransactionBuilder.newTransacion(tracerHolder, TracerTransactionTypeEnum.TASK.getName(), taskName);
        this.taskName = taskName;
    }

    public void finish(){
        this.tracerTransaction.finish();
    }

    public void result(boolean result){
        this.tracerTransaction.result(result);
    }
}
