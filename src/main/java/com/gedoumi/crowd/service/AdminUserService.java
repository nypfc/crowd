package com.gedoumi.crowd.service;

import com.gedoumi.crowd.dao.AdminUserDao;
import com.gedoumi.crowd.dataobj.model.AdminUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
