package info.xpanda.labs.monitor.core.transaction;

import info.xpanda.labs.monitor.core.metric.MonitorMetric;
import info.xpanda.labs.monitor.core.opentracing.TracerTags;
import io.opentracing.Span;
import io.opentracing.Tracer;

import java.util.HashMap;
import java.util.Map;

public class TransactionHelper {
    public static void logTransaction(Span span, TracerTransactionTypeEnum type){
        span.setTag("type", type.getName());
    }

    public static void logEvent(Span span, TracerEventTypeEnum event) {
        span.setTag("type", event.getName());
    }

    public static void logEvent(Span span, TracerEventTypeEnum event, MonitorMetric metric) {
        span.setTag("type", event.getName());
        span.setTag("metric", metric.getName());
        span.setTag("value", metric.getValue());
    }

    public static void logError(Span span, boolean error){
        span.setTag(TracerTags.TAG_ERROR, error);
    }
}
