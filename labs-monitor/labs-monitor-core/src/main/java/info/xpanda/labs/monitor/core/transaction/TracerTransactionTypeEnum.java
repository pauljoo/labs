package info.xpanda.labs.monitor.core.transaction;

public enum TracerTransactionTypeEnum {
    /**
     * 请求
     */
    URL(200, "url"),
    /**
     * 定时任务
     */
    TASK(201, "task");

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
