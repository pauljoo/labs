package info.xpanda.labs.monitor.core.transaction;

import info.xpanda.labs.monitor.core.metric.MonitorMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EventHelper {
    private static final Logger log = LoggerFactory.getLogger("metric");

    public static void logEvent(String name, TracerEventTypeEnum event) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("type",  event.getName());
        log.info(map.toString());
    }

    public static void logEvent(String name, TracerEventTypeEnum event, MonitorMetric metric) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("type",  event.getName());
        map.put("metric", metric.getName());
        map.put("value", metric.getValue());
        log.info(map.toString());
    }
}
