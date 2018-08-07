package info.xpanda.labs.monitor.core.transaction;

import info.xpanda.labs.monitor.core.opentracing.TracerTags;
import io.opentracing.Span;

public class TransactionHelper {
    public static void logTransaction(Span span, TracerTransactionTypeEnum type){
        span.setTag("type", type.getName());
    }

    public static void logError(Span span, boolean error){
        span.setTag(TracerTags.TAG_ERROR, error);
    }
}
