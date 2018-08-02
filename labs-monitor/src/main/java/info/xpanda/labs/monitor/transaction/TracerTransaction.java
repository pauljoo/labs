package info.xpanda.labs.monitor.transaction;

import info.xpanda.labs.monitor.opentracing.TracerTags;
import io.opentracing.Span;

public class TracerTransaction {
    private Span span;

    public TracerTransaction(Span span) {
        this.span = span;
    }

    public void finish(){
        this.span.finish();
    }

    public void result(boolean result){
        span.setTag(TracerTags.TAG_ERROR, result);
    }

    public Span getSpan() {
        return span;
    }
}
