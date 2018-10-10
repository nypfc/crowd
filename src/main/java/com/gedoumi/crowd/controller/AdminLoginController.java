package com.gedoumi.crowd.controller;

import com.gedoumi.crowd.common.utils.LoginUtil;
import com.gedoumi.crowd.dataobj.form.AdminLoginForm;
import com.gedoumi.crowd.dataobj.vo.ResponseObject;
import com.gedoumi.crowd.service.AdminUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 管理后台用户登录Controller
 *
 * @author Minced
 */
@RequestMapping("/admin")
@RestController
public class AdminLoginController {

    @Resource
    private AdminUserService adminUserService;

    /**
     * 后台用户登录
     *
     * @param adminLoginForm 后台登录表单
     * @return ResponseObject
     */
    @PostMapping("/login")
    public ResponseObject login(@RequestBody AdminLoginForm adminLoginForm) {
        // 设置Cookie
        LoginUtil.setAuthTokenCookie(adminUserService.userLogin(adminLoginForm));
        return ResponseObject.setSuccessResponse();
    }

}
