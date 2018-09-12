package info.xpanda.labs.spring.cloud.jpa.dao;

import info.xpanda.labs.spring.cloud.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>{
    UserEntity findByUsernameAndPassword(String userName, String password);
}
