package com.gedoumi.crowd.user.service;

import com.gedoumi.crowd.common.exception.CrowdException;
import com.gedoumi.crowd.common.utils.LoginUtil;
import com.gedoumi.crowd.user.dataobj.form.LoginForm;
import com.gedoumi.crowd.user.dataobj.model.User;
import com.gedoumi.crowd.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.NO_LOGIN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 用户Service
 *
 * @author Minced
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过令牌获取用户
     *
     * @param token 令牌
     * @return 用户对象
     */
    public User getUser(String token) {
        return Optional.of(userMapper.queryByToken(token)).orElseThrow(() -> {
            log.error("\"Token={}\"未查询到用户", token);
            return new CrowdException(BAD_REQUEST, NO_LOGIN);
        });
    }

    /**
     * 用户登录
     *
     * @param loginForm 登录表单
     * @return 令牌
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String userLogin(LoginForm loginForm) {
        // 1.根据UID查询
        User user = userMapper.queryByUid(loginForm.getUid());
        if (user == null) return createUser(loginForm);  // 如果未查询到用户则创建用户

        // 2.设置Token
        String token = LoginUtil.createToken();
        userMapper.updateTokenAndLastLoginTime(user.getId(), token);
        return token;
    }

    /**
     * 更新用户积分
     *
     * @param userId 用户ID
     * @param point  增加的积分量
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void updateUserPoint(Long userId, Long point) {
        userMapper.updateUserPoint(userId, point);
    }

    /**
     * 创建用户对象
     *
     * @return 令牌
     */
    private String createUser(LoginForm loginForm) {
        User user = new User();
        user.setUid(loginForm.getUid());
        user.setNickname(loginForm.getNickname());
        user.setAvatar(loginForm.getAvatar());
        String token = LoginUtil.createToken();
        user.setToken(token);
        // TODO 邀请码的判断
        if (loginForm.getInvitedCode() == null) {
            user.setInvitedCode("yaoqingma");
        } else {
            user.setInvitedCode(loginForm.getInvitedCode());
        }
        userMapper.createUser(user);
        return token;
    }

}
