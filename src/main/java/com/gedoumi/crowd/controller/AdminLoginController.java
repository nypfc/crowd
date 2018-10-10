package com.gedoumi.crowd.controller;

import com.gedoumi.crowd.dataobj.form.AdminLoginForm;
import com.gedoumi.crowd.dataobj.vo.ResponseObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 *
 * @author Minced
 */
@RequestMapping("/admin")
@RestController
public class AdminLoginController {

    /**
     * 后台用户登录
     *
     * @param adminLoginForm 后台登录表单
     * @return ResponseObject
     */
    @PostMapping("/login")
    public ResponseObject login(@RequestBody AdminLoginForm adminLoginForm) {
        System.out.println(adminLoginForm);
        return ResponseObject.setSuccessResponse("假装是返回数据");
    }

}
