package info.xpanda.labs.monitor.opentracing;

public enum TracerLogEnum {
    /**
     * 度量
     */
    METRIC(1, "metric");

    private int id;

    private String name;

    private TracerLogEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
