package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.dataobj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 用户Dao
 *
 * @author Minced
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据令牌查询
     *
     * @param token 令牌
     * @return 用户对象
     */
    Optional<User> findByToken(String token);

    /**
     * 根据第三方UID查询
     *
     * @param uid 第三方UID
     * @return 用户对象
     */
    Optional<User> findByUid(String uid);

}
