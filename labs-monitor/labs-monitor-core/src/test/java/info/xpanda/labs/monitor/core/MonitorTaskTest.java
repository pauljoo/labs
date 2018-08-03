package info.xpanda.labs.monitor.core;

import info.xpanda.labs.monitor.core.opentracing.TracerHolder;
import junit.framework.TestCase;

public class MonitorTaskTest extends TestCase {
    public MonitorTaskTest(String name) {
        super(name);
    }
    public void testTask() {
        TracerHolder tracerHolder = new TracerHolder("test_task");
        tracerHolder.init();

        MonitorTask monitorTask = new MonitorTask(tracerHolder, "xpanda:task");
        try {
            //开始进行跑批任务
        } catch (Exception ex) {
            monitorTask.error(false);
        }
        monitorTask.finish();
    }
}