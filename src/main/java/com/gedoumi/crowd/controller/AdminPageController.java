package com.gedoumi.crowd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 *
 * @author Minced
 */
@RequestMapping("/admin")
@Controller
public class AdminPageController {

    /**
     * 登录页跳转
     *
     * @return 登录页面路径
     */
    @GetMapping("/loginPage")
    public String loginPage() {
        return "/loginPage";
    }

    /**
     * 主页面跳转
     *
     * @return 主页面路径
     */
    @GetMapping("/indexPage")
    public String indexPage() {
        return "/indexPage";
    }

    /**
     * 用户管理页面跳转
     *
     * @return 用户管理页面路径
     */
    @GetMapping("/userPage")
    public String userPage() {
        return "/admin/userPage";
    }

}
