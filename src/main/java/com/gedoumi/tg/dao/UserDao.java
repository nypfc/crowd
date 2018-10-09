package com.gedoumi.tg.dao;

import com.gedoumi.tg.dataobj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 用户Dao
 *
 * @author Minced
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据Token查询
     * @param token Token
     * @return 用户对象
     */
    Optional<User> findByToken(String token);

}
