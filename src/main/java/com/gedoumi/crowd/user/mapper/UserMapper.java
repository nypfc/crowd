package com.gedoumi.crowd.user.mapper;

import com.gedoumi.crowd.user.dataobj.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 *
 * @author Minced
 */
@Mapper
public interface UserMapper {

    /**
     * 根据令牌查询用户
     *
     * @param token 令牌
     * @return 用户对象
     */
    User queryByToken(String token);

    /**
     * 根据第三方UID查询用户
     *
     * @param uid 第三方UID
     * @return 用户对象
     */
    User queryByUid(String uid);

    /**
     * 更新用户令牌与最后登录时间
     *
     * @param userId 用户ID
     * @param token  令牌
     */
    void updateTokenAndLastLoginTime(Long userId, String token);

    /**
     * 创建用户
     *
     * @param user 用户对象
     */
    void createUser(User user);

    /**
     * 更新用户积分
     *
     * @param userId 用户ID
     * @param point  增加的积分量
     */
    void updateUserPoint(Long userId, Long point);

}
