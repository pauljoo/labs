package info.xpanda.labs.monitor.transaction;

import info.xpanda.labs.monitor.metric.MonitorMetric;
import info.xpanda.labs.monitor.opentracing.TracerHolder;

import java.util.HashMap;
import java.util.Map;

public class TracerTransactionBuilder {
    public static TracerTransaction newTransacion(TracerHolder tracerHolder, String type, String name){
        return new TracerTransaction(tracerHolder.getTracer().buildSpan(name).withTag("type", type).start());
    }

    public static void newFinshTransacion(TracerHolder tracerHolder, TracerTransactionTypeEnum type, String name, TracerEventTypeEnum event){
        TracerTransaction tracerTransaction = new TracerTransaction(tracerHolder.getTracer().buildSpan(name).withTag("type", type.getName()).start());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", event.getName());
        map.put("name", name);
        tracerTransaction.getSpan().log(map);
        tracerTransaction.finish();
    }

    public static void newFinshTransacion(TracerHolder tracerHolder, TracerTransactionTypeEnum type, String name, TracerEventTypeEnum event, MonitorMetric metric){
        TracerTransaction tracerTransaction = new TracerTransaction(tracerHolder.getTracer().buildSpan(name).withTag("type", type.getName()).start());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", event.getName());
        map.put("name", name);
        map.put("metric", metric.getName());
        map.put("value", metric.getValue());
        tracerTransaction.getSpan().log(map);
        tracerTransaction.finish();
    }
}
