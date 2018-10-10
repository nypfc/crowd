package com.gedoumi.crowd.api.controller;

import com.gedoumi.crowd.api.dataobj.form.LoginForm;
import com.gedoumi.crowd.api.dataobj.vo.ResponseObject;
import com.gedoumi.crowd.api.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * 登陆Controller
 *
 * @author Minced
 */
@RequestMapping("/user")
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param loginForm 登陆表单
     * @return ResponseObject
     */
    @PostMapping("/login")
    public ResponseObject userLogin(@RequestBody @Valid LoginForm loginForm) {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("token", userService.userLogin(loginForm));
        return ResponseObject.setSuccessResponse(map);
    }

}
