package info.xpanda.labs.monitor.core;

import info.xpanda.labs.monitor.core.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.core.metric.MonitorMetric;
import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import junit.framework.TestCase;


public class MonitorHeatbeatTest extends TestCase {
    public MonitorHeatbeatTest(String name) {
        super(name);
    }

    public void testHeatbeat() {
        TracerHolder tracerHolder = new TracerHolder("test_Heatbeat");
        tracerHolder.init();

        MonitorHeartbeat.logHeartbeat(tracerHolder, "xpanda:global:cpu", new MonitorMetric(MetricTypeEnum.GAUGE, "70"));
    }
}