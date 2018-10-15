package com.gedoumi.crowd.user.controller;

import com.gedoumi.crowd.common.utils.ContextUtil;
import com.gedoumi.crowd.common.utils.ResponseObject;
import com.gedoumi.crowd.user.dataobj.form.LoginForm;
import com.gedoumi.crowd.user.dataobj.model.User;
import com.gedoumi.crowd.user.dataobj.vo.UserVO;
import com.gedoumi.crowd.user.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * 登陆Controller
 *
 * @author Minced
 */
@RequestMapping("/api/user")
@RestController
public class ApiLoginController {

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

    /**
     * 获取用户数据
     *
     * @return ResponseObject
     */
    @GetMapping("/get")
    public ResponseObject getUser() {
        User user = ContextUtil.getUserFromRequest();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResponseObject.setSuccessResponse(userVO);
    }

}
