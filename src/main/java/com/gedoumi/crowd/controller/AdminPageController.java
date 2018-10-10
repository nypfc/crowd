package com.gedoumi.crowd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author Minced
 */
@RequestMapping("/admin")
@Controller
public class AdminPageController {

    /**
     * 登录页跳转
     * @return 登录页面路径
     */
    @GetMapping("/loginPage")
    public String loginPage() {
        return "/loginPage";
    }

}
