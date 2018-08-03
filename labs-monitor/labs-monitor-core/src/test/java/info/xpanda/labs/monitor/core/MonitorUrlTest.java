package info.xpanda.labs.monitor.core;

import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
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
            monitorUrl.error(false);
        }
        monitorUrl.finish();
    }

    public void testUrlWithinEvent() {
        TracerHolder tracerHolder = new TracerHolder("test_url");
        tracerHolder.init();

        MonitorUrl monitorUrl = new MonitorUrl(tracerHolder, "/url");
        try {
        } catch (Exception ex) {
            monitorUrl.error(false);
        }
        monitorUrl.finish();
    }
}