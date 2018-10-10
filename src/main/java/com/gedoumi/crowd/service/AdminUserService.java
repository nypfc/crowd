package com.gedoumi.crowd.service;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.common.utils.HashUtil;
import com.gedoumi.crowd.common.utils.LoginUtil;
import com.gedoumi.crowd.dao.AdminUserDao;
import com.gedoumi.crowd.dataobj.form.AdminLoginForm;
import com.gedoumi.crowd.dataobj.model.AdminUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.USERNAME_OR_PASSWORD_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 管理后台用户Service
 *
 * @author Minced
 */
@Service
public class AdminUserService {

    @Resource
    public AdminUserDao adminUserDao;

    /**
     * 通过令牌获取后台用户
     *
     * @param token 令牌
     * @return 后台用户对象
     */
    public AdminUser getUser(String token) {
        return adminUserDao.findByToken(token);
    }

    /**
     * 管理后台用户登录
     *
     * @param adminLoginForm 后台登录表单
     * @return 令牌
     */
    public String userLogin(AdminLoginForm adminLoginForm) {
        // 1.查询用户并对比密码
        String loginUsername = adminLoginForm.getUsername();
        String loginPassword = adminLoginForm.getPassword();
        AdminUser user = adminUserDao.findByUsername(loginUsername).orElseThrow(() -> new TgException(UNAUTHORIZED, USERNAME_OR_PASSWORD_ERROR));
        String encryPassword = HashUtil.encryPassword(loginUsername, loginPassword);
        if (!user.getPassword().equals(encryPassword)) {
            throw new TgException(UNAUTHORIZED, USERNAME_OR_PASSWORD_ERROR);
        }

        // 2.保存令牌以及最后登录时间
        String token = LoginUtil.createToken();
        user.setToken(token);
        user.setLastLoginTime(new Date());
        adminUserDao.save(user);

        // 3.返回令牌
        return token;
    }

}
