package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.dataobj.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 管理后台用户Dao
 *
 * @author Minced
 */
public interface AdminUserDao extends JpaRepository<AdminUser, Long> {

    /**
     * 根据令牌查询
     *
     * @param token 令牌
     * @return 后台用户
     */
    AdminUser findByToken(String token);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 后台用户
     */
    Optional<AdminUser> findByUsername(String username);

}
