package com.gedoumi.crowd.user.mapper;

import com.gedoumi.crowd.user.dataobj.model.AdminUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理后台用户Mapper
 *
 * @author Minced
 */
@Mapper
public interface AdminUserMapper {

    /**
     * 根据令牌查询后台管理用户
     *
     * @param token 令牌
     * @return 后台管理用户对象
     */
    AdminUser queryByToken(String token);

    /**
     * 根据用户名查询后台管理用户
     *
     * @param username 用户名
     * @return 后台管理用户对象
     */
    AdminUser queryByUsername(String username);

    /**
     * 更新令牌和最后登录时间
     *
     * @param userId 后台用户ID
     * @param token  令牌
     */
    void updateTokenAndLastLoginTime(Long userId, String token);

}
