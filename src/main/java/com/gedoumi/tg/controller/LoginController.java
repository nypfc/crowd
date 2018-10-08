package com.gedoumi.tg.controller;

import com.gedoumi.tg.dataobj.vo.ResponseObject;
import com.gedoumi.tg.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登陆Controller
 *
 * @author Minced
 */
@RequestMapping("/user")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 用户登录
     *
     * @return ResponseObject
     */
    @PostMapping("/login")
    public ResponseObject userLogin() {
        return ResponseObject.setSuccessResponse();
    }

}
