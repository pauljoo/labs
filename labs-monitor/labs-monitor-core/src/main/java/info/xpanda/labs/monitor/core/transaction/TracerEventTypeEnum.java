package info.xpanda.labs.monitor.core.transaction;

public enum TracerEventTypeEnum {
    /**
     * 心跳
     */
    HEARTBEAT(100, "heartbeat"),
    /**
     * 事件
     */
    EVENT(200, "event");

    private int id;

    private String name;

    private TracerEventTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
