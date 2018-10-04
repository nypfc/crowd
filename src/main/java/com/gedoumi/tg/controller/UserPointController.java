package com.gedoumi.tg.controller;

import com.gedoumi.tg.dataobj.model.User;
import com.gedoumi.tg.dataobj.vo.ResponseObject;
import com.gedoumi.tg.service.UserPointService;
import com.gedoumi.tg.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户积分Controller
 *
 * @author Minced
 */
@RestController
public class UserPointController {

    @Resource
    private UserPointService userPointService;
    @Resource
    private UserService userService;

    /**
     * 每日助力
     *
     * @return ResponseEntity
     */
    @PostMapping("/addPoint")
    public ResponseObject addPoint() {
        User user = userService.getUser();
        userPointService.addPoint(user);
        return ResponseObject.setSuccessResponse();
    }

}
