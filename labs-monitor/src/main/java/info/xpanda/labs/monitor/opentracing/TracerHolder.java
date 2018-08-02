package info.xpanda.labs.monitor.opentracing;

import info.xpanda.labs.monitor.opentracing.reporter.MonitorLoggingRepoter;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.propagation.B3TextMapCodec;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.spi.Codec;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;

/**
 * Tag标记,Log事件
 * 1分钟1次heartbeat:GC,线程,内存,,
 * 业务监控每次发生:Event()
 * 请求监控每次发生: span.kind=server, http.url
 * 远程调用监控: span.kind=client, component.jdbc
 * 跑批监控事务型: span.error=error
 * 依赖监控:分析调用情况
 *
 * ===============================
 *
 *
 * 定时心跳：全部采样
 * {ParentId:,TraceId:,SpanId:, Type:heartbeat, Name:cpu, log(type:heartbeat, name:cpu, metric:gauge, value:100)}
 * 普通请求
 * {ParentId:,TraceId:,SpanId:, Type:url, Name:/url.action,log()}
 * 绑定在当前的跟踪中
 * {ParentId:,TraceId:,SpanId:, Type:url, Name:/url.action, log(type:event, name:trade, metric:sum, value:100)}
 * 独立的单次记录：部分采样
 * {ParentId:,TraceId:,SpanId:, Type:event, Name:eventName, log(type:event, name:trade, metric:sum, value:100)}
 * 跑批：全部采样
 * {ParentId:,TraceId:,SpanId:, Type:task, Name:tastName}
 */
public class TracerHolder {
    private Tracer tracer;

    private String serviceName;

    public TracerHolder(String serviceName) {
        this.serviceName = serviceName;
    }

    public void init(){
        Codec b3Codec = new B3TextMapCodec.Builder().build();
        this.tracer = new JaegerTracer.Builder(serviceName)
                .withReporter(new MonitorLoggingRepoter())
                .withSampler(new ConstSampler(true))
                .registerInjector(Format.Builtin.HTTP_HEADERS, b3Codec)
                .registerExtractor(Format.Builtin.HTTP_HEADERS, b3Codec)
                .build();
    }

    public Tracer getTracer() {
        return tracer;
    }
}
