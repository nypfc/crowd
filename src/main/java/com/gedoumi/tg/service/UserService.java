package com.gedoumi.tg.service;

import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.UserDao;
import com.gedoumi.tg.dataobj.form.LoginForm;
import com.gedoumi.tg.dataobj.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.gedoumi.tg.common.constants.ResponseMessage.NO_LOGIN;
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

    @Resource
    private HttpServletRequest request;

    /**
     * 从Request作用域中取出用户
     *
     * @return 用户对象
     */
    public User getUserFromRequest() {
        User user = (User) request.getAttribute("user");
        if (user == null) {
            log.error("未能从Request作用域中获取到user");
            throw new TgException(BAD_REQUEST, NO_LOGIN);
        }
        return user;
    }

    /**
     * 通过令牌获取用户
     *
     * @param token 令牌
     * @return 用户对象
     */
    public User getUser(String token) {
        return userDao.findByToken(token).orElseThrow(() -> {
            log.warn("\"Token={}\"未查询到用户", token);
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
        return null;
    }

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void userRegister(User user) {
        userDao.save(user);
    }

}
