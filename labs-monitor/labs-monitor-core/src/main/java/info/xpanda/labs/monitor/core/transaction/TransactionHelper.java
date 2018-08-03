package info.xpanda.labs.monitor.core.transaction;

import info.xpanda.labs.monitor.core.metric.MonitorMetric;
import info.xpanda.labs.monitor.core.opentracing.TracerTags;
import io.opentracing.Span;

import java.util.HashMap;
import java.util.Map;

public class TransactionHelper {

    public static void newTransaction(Span span, TracerTransactionTypeEnum type){
        span.setTag("type", type.getName());
    }

    public static void logEvent(Span span, String eventName, TracerEventTypeEnum event) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", event.getName());
        map.put("name", eventName);
        span.log(map);
    }

    public static void logEvent(Span span, String eventName, TracerEventTypeEnum event, MonitorMetric metric) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", event.getName());
        map.put("name", eventName);
        map.put("metric", metric.getName());
        map.put("value", metric.getValue());
        span.log(map);
    }

    public static void logError(Span span, boolean error){
        span.setTag(TracerTags.TAG_ERROR, error);
    }
}
