package info.xpanda.labs.monitor;

import info.xpanda.labs.monitor.opentracing.TracerHolder;
import junit.framework.TestCase;

import static org.junit.Assert.*;

public class TracerTaskTest extends TestCase {

    public void testTask() {
        TracerHolder tracerHolder = new TracerHolder("test_task");
        tracerHolder.init();

        TracerTask tracerTask = new TracerTask(tracerHolder, "cawler task");
        try {
            //开始进行跑批任务
        } catch (Exception ex) {
            tracerTask.result(false);
        }
        tracerTask.finish();
    }
}