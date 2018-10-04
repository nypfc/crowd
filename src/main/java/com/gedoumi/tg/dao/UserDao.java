package com.gedoumi.tg.dao;

import com.gedoumi.tg.dataobj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户Dao
 *
 * @author Minced
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据Token查询数量
     * @param token Token
     * @return 查询结果数量
     */
    Integer countByToken(String token);

    /**
     * 根据Token查询
     * @param token Token
     * @return 用户对象
     */
    User findByToken(String token);

}
