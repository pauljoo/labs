package info.xpanda.labs.monitor.core.metric;

public enum MetricTypeEnum {
    /**
     * 当前测量值
     */
    GAUGE(100, "gauge"),
    /**
     * 计数器
     */
    COUNT(200, "count"),
    /**
     * 加
     */
    SUM(201, "sum"),
    AVG(202, "avg"),
    MAX(203, "max"),
    MIN(204, "min"),
    STD(205, "std"),
    /**
     * 用时
     */
    TIMER(300, "timer");

    private int id;
    private String name;

    private MetricTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
