package info.xpanda.labs;

public class Main {
    public static void main(String[] args) {
        try {
            while(true){
                Hello hello = new Hello();
                hello.sayHello();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
