package info.xpanda.labs.monitor.transaction;

import info.xpanda.labs.monitor.metric.MonitorMetric;
import info.xpanda.labs.monitor.opentracing.TracerHolder;
import info.xpanda.labs.monitor.opentracing.TracerLogEnum;
import info.xpanda.labs.monitor.opentracing.TracerTags;

import java.util.HashMap;
import java.util.Map;

public class TracerTransactionBuilder {
    public static TracerTransaction newTransacion(TracerHolder tracerHolder, String type, String name){
        return new TracerTransaction(tracerHolder.getTracer().buildSpan(name).withTag("type", type).start());
    }

    public static void newFinshTransacion(TracerHolder tracerHolder, String type, String name){
        TracerTransaction tracerTransaction = new TracerTransaction(tracerHolder.getTracer().buildSpan(name).withTag("type", type).start());
        tracerTransaction.finish();
    }

    public static void newFinshTransacion(TracerHolder tracerHolder, String type, String name, MonitorMetric metric){
        TracerTransaction tracerTransaction = new TracerTransaction(tracerHolder.getTracer().buildSpan(name).withTag("type", type).start());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", TracerLogEnum.METRIC.getName());
        map.put("name", metric.getName());
        map.put("value", metric.getValue());
        tracerTransaction.getSpan().log(map);
        tracerTransaction.finish();
    }
}
