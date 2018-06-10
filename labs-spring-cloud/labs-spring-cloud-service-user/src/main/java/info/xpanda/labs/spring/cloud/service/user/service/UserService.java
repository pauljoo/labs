package info.xpanda.labs.spring.cloud.service.user.service;

public interface UserService {
    String info();

    String tcc(Long id, String name);

    String confirmTcc();

    String cancelTcc();
}
