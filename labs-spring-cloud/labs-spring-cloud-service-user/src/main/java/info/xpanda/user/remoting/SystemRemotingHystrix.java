package info.xpanda.user.remoting;


public class SystemRemotingHystrix implements SystemRemoting {
    @Override
    public String info() {
        return "Call System Error: info";
    }

    @Override
    public String tcc() {
        return "Call System Error: tcc";
    }

    @Override
    public String confirmTcc() {
        return "Call System Error: confirmTcc";
    }

    @Override
    public String cancelTcc() {
        return "Call System Error: cancelTcc";
    }
}
