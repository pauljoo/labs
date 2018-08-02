package info.xpanda.labs.monitor.metric;

public enum MetricTypeEnum {
    /**
     * 当前测量值
     */
    GAUGE(100),
    /**
     * 计数器
     */
    COUNT(200),
    /**
     * 加
     */
    SUM(201),
    AVG(202),
    MAX(203),
    MIN(204),
    STD(205),
    /**
     * 用时
     */
    TIMER(300);

    private int id;

    private MetricTypeEnum(int id){
        this.id = id;
    }
}
