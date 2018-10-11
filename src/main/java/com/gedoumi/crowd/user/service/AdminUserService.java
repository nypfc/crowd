package com.gedoumi.crowd.user.service;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.common.utils.HashUtil;
import com.gedoumi.crowd.common.utils.LoginUtil;
import com.gedoumi.crowd.user.dataobj.form.AdminLoginForm;
import com.gedoumi.crowd.user.dataobj.model.AdminUser;
import com.gedoumi.crowd.user.mapper.AdminUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.USERNAME_OR_PASSWORD_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 管理后台用户Service
 *
 * @author Minced
 */
@Service
public class AdminUserService {

    @Resource
    public AdminUserMapper adminUserMapper;

    /**
     * 通过令牌获取后台用户
     *
     * @param token 令牌
     * @return 后台用户对象
     */
    public AdminUser getUser(String token) {
        return adminUserMapper.queryByToken(token);
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
        AdminUser user = Optional.of(adminUserMapper.queryByUsername(loginUsername))
                .orElseThrow(() -> new TgException(UNAUTHORIZED, USERNAME_OR_PASSWORD_ERROR));
        String encryPassword = HashUtil.encryPassword(loginUsername, loginPassword);
        if (!user.getPassword().equals(encryPassword)) {
            throw new TgException(UNAUTHORIZED, USERNAME_OR_PASSWORD_ERROR);
        }

        // 2.更新令牌以及最后登录时间
        String token = LoginUtil.createToken();
        adminUserMapper.updateTokenAndLastLoginTime(user.getId(), token);

        // 3.返回令牌
        return token;
    }

}
