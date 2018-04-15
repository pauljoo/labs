package info.xpanda.user.service;

public interface UserService {
    String info();

    String tcc(Long id, String name);

    String confirmTcc();

    String cancelTcc();
}
