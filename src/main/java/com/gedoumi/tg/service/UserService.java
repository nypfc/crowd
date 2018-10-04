package com.gedoumi.tg.service;

import com.gedoumi.tg.common.enums.CodeEnum;
import com.gedoumi.tg.common.exception.LoginException;
import com.gedoumi.tg.dao.UserDao;
import com.gedoumi.tg.dataobj.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户Service
 *
 * @author Minced
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private HttpServletRequest request;

    /**
     * 获取用户
     *
     * @return 用户
     */
    public User getUser() {
        String token = request.getHeader("auth-token");
        if (token == null) throw new LoginException(CodeEnum.NO_AUTH_TOKEN);
        User user = userDao.findByToken(token);
        if (user == null) throw new LoginException(CodeEnum.USER_NOT_EXIST);
        return user;
    }

}
