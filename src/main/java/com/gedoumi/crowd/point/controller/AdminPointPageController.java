package com.gedoumi.crowd.point.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理后台积分相关跳转Controller
 *
 * @author Minced
 */
@RequestMapping("/admin")
@Controller
public class AdminPointPageController {

    /**
     * 积分明细页面跳转
     *
     * @return 积分明细页面路径
     */
    @GetMapping("/pointPage")
    public String pointPage() {
        return "/pointPage";
    }

}
