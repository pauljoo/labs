package info.xpanda.user.remoting;

public class SystemRemotingHystrix implements SystemRemoting {
    @Override
    public String info() {
        return "Call System Error: info";
    }
}
