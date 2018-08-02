package info.xpanda.labs.monitor.opentracing;

public enum TracerTypeEnum {
    /**
     * 度量
     */
    METRIC(1),
    /**
     * 事务
     */
    TRANSACTION(2);

    private int id;

    private TracerTypeEnum(int id){
        this.id = id;
    }
}
