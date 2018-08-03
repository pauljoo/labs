package info.xpanda.labs.monitor.core.metric;

public class MonitorMetric {
    private String name;

    private Object value;

    public MonitorMetric(MetricTypeEnum type, Object value) {
        this.name = type.getName();
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
