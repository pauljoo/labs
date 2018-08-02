package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.opentracing.TracerHolder;
import junit.framework.TestCase;

public class MonitorUrlTest extends TestCase {
    public MonitorUrlTest(String name) {
        super(name);
    }

    public void testUrl() {
        TracerHolder tracerHolder = new TracerHolder("test_url");
        tracerHolder.init();

        MonitorUrl monitorUrl = new MonitorUrl(tracerHolder, "/url");
        try {
        } catch (Exception ex) {
            monitorUrl.result(false);
        }
        monitorUrl.finish();
    }
}