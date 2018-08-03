package info.xpanda.labs.monitor.spring.annonations;

public enum Propagation {
    REQUIRED(1),
    REQUIRES_NEW(2);

    private int id;

    private Propagation(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
