package info.xpanda.labs.monitor.core.transaction;

public enum TracerTransactionTypeEnum {
    /**
     * 心跳
     */
    HEARTBEAT(100, "heartbeat"),
    /**
     * 请求
     */
    URL(200, "url"),
    /**
     * 事件
     */
    EVENT(201, "event"),
    /**
     * 定时任务
     */
    TASK(202, "task");

    private int id;

    private String name;

    private TracerTransactionTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
