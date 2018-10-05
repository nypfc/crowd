package com.gedoumi.tg.service;

import com.gedoumi.tg.common.enums.CodeEnum;
import com.gedoumi.tg.common.exception.TgException;
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
    private HttpServletRequest request;

    /**
     * 获取用户
     * @return 用户对象
     */
    public User getUser() {
        // 通过request作用域获取用户对象
        User user = (User) request.getAttribute("user");
        if (user == null) throw new TgException(CodeEnum.USER_NOT_LOGIN);
        return user;
    }

}
