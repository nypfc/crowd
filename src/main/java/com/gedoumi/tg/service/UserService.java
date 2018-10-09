package com.gedoumi.tg.service;

import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.UserDao;
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
     * 获取用户
     *
     * @return 用户对象
     */
    public User getUser() {
        // 通过request作用域获取用户对象
        User user = (User) request.getAttribute("user");
        if (user == null) {
            log.warn("user查询结果为空");
            throw new TgException(BAD_REQUEST, NO_LOGIN);
        }
        return user;
    }

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    @Transactional
    public void userRegister(User user) {
        userDao.save(user);
    }

}
