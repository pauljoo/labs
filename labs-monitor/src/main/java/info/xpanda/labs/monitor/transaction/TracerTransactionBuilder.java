package info.xpanda.labs.monitor.transaction;

import info.xpanda.labs.monitor.opentracing.TracerHolder;

public class TracerTransactionBuilder {
    public static TracerTransaction newTransacion(TracerHolder tracerHolder, String type, String name){
        return new TracerTransaction(tracerHolder.getTracer().buildSpan(name).withTag("type", type).start());
    }

    public static void newFinshTransacion(TracerHolder tracerHolder, String type, String name){
        TracerTransaction tracerTransaction = new TracerTransaction(tracerHolder.getTracer().buildSpan(type).withTag("type", type).start());
        tracerTransaction.finish();
    }
}
