package com.gedoumi.tg.controller;

import com.gedoumi.tg.service.LoginService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登陆Controller
 *
 * @author Minced
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

}
