package info.xpanda.labs.monitor.opentracing.reporter;

import com.alibaba.fastjson.JSONObject;
import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.spi.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorLoggingRepoter implements Reporter {
    private final static Logger log = LoggerFactory.getLogger("monitor");

    public void report(JaegerSpan span) {
        JSONObject map = new JSONObject();
        map.put("trace_id", Long.toHexString(span.context().getTraceId()));
        map.put("parent_id", Long.toHexString(span.context().getParentId()));
        map.put("span_id", Long.toHexString(span.context().getSpanId()));
        map.put("service_name", span.getServiceName());
        map.put("operation_name", span.getOperationName());
        map.put("start", span.getStart());
        map.put("duration", span.getDuration());
        map.put("tags", span.getTags());
        log.info(JSONObject.toJSONString(map));
    }

    public void close() {

    }
}
