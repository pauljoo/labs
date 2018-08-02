package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.metric.MonitorMetric;
import info.xpanda.labs.monitor.opentracing.TracerHolder;
import junit.framework.TestCase;

public class MonitorEventTest extends TestCase {
    public MonitorEventTest(String name) {
        super(name);
    }

    public void testEvent() {
        TracerHolder tracerHolder = new TracerHolder("test_Event");
        tracerHolder.init();

        MonitorEvent.logEvent(tracerHolder, "xpanda:trade", new MonitorMetric(MetricTypeEnum.SUM, 100));
    }
}