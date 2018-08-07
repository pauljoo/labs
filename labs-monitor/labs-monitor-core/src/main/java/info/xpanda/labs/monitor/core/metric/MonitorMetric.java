package info.xpanda.labs.monitor.core.metric;

public class MonitorMetric {
    private String name;

    private String value;

    public MonitorMetric(MetricTypeEnum type, String value) {
        this.name = type.getName();
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
