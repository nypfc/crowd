package com.gedoumi.crowd.service;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.common.utils.LoginUtil;
import com.gedoumi.crowd.dao.UserDao;
import com.gedoumi.crowd.dataobj.form.LoginForm;
import com.gedoumi.crowd.dataobj.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    private UserDao userDao;

    /**
     * 通过令牌获取用户
     *
     * @param token 令牌
     * @return 用户对象
     */
    public User getUser(String token) {
        return userDao.findByToken(token).orElseThrow(() -> {
            log.error("\"Token={}\"未查询到用户", token);
            return new TgException(BAD_REQUEST, NO_LOGIN);
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
        // 根据UID查询
        User user = userDao.findByUid(loginForm.getUid()).orElse(createUser(loginForm));
        // 设置Token
        user.setToken(LoginUtil.createToken());
        user.setLastLoginTime(new Date());
        userDao.save(user);
        return user.getToken();
    }

    /**
     * 更新用户积分
     *
     * @param user 用户对象
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void updateUserPoint(User user) {
        userDao.save(user);
    }

    /**
     * 获取指定ID的用户
     *
     * @param userIdList 用户ID集合
     * @return 用户集合
     */
    public List<User> getUserByIdList(List<Long> userIdList) {
        return userDao.findAllById(userIdList);
    }

    /**
     * 创建用户对象
     *
     * @return 用户对象
     */
    private User createUser(LoginForm loginForm) {
        User user = new User();
        user.setUid(loginForm.getUid());
        user.setNickname(loginForm.getNickname());
        user.setAvatar(loginForm.getAvatar());
        // TODO 邀请码的判断
        if (loginForm.getInvitedCode() == null) {
            user.setInvitedCode("yaoqingma");
        } else {
            user.setInvitedCode(loginForm.getInvitedCode());
        }
        return user;
    }

}
