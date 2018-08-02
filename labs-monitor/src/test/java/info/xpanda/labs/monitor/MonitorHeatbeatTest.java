package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.metric.MonitorMetric;
import info.xpanda.labs.monitor.opentracing.TracerHolder;
import junit.framework.TestCase;


public class MonitorHeatbeatTest extends TestCase {
    public MonitorHeatbeatTest(String name) {
        super(name);
    }

    public void testHeatbeat() {
        TracerHolder tracerHolder = new TracerHolder("test_Heatbeat");
        tracerHolder.init();

        MonitorHeatbeat.logHeatbeat(tracerHolder, "xpanda:global:cpu", new MonitorMetric(MetricTypeEnum.GAUGE, 70));
    }
}