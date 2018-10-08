package com.gedoumi.tg.controller;

import com.gedoumi.tg.dataobj.model.User;
import com.gedoumi.tg.dataobj.model.UserPointDetail;
import com.gedoumi.tg.dataobj.vo.ResponseObject;
import com.gedoumi.tg.dataobj.vo.UserPointVO;
import com.gedoumi.tg.service.UserPointDetailService;
import com.gedoumi.tg.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户积分Controller
 *
 * @author Minced
 */
@RequestMapping("/point")
@RestController
public class UserPointController {

    @Resource
    private UserPointDetailService userPointDetailService;
    @Resource
    private UserService userService;

    /**
     * 获取用户当前积分
     *
     * @return ResponseObject
     */
    @GetMapping("/get")
    public ResponseObject userPoint() {
        User user = userService.getUser();
        HashMap<String, Long> map = Maps.newHashMap();
        map.put("userPoint", user.getPoint());
        return ResponseObject.setSuccessResponse(map);
    }

    /**
     * 每日积分助力
     *
     * @return ResponseObject
     */
    @PostMapping("/add")
    public ResponseObject addPoint() {
        User user = userService.getUser();
        userPointDetailService.addPoint(user);
        return ResponseObject.setSuccessResponse();
    }

    /**
     * 用户助力积分明细
     *
     * @return ResponseObject
     */
    @GetMapping("/list")
    public ResponseObject userPointList() {
        User user = userService.getUser();
        List<UserPointDetail> userPointDetailList = userPointDetailService.getUserPointDetailList(user.getId());
        // 封装返回数据
        return ResponseObject.setSuccessResponse(userPointDetailList.stream().map(userPointDetail -> {
            UserPointVO userPointVO = new UserPointVO();
            BeanUtils.copyProperties(userPointDetail, userPointVO);
            return userPointVO;
        }).collect(Collectors.toList()));
    }

}
