package com.gedoumi.crowd.controller;

import com.gedoumi.crowd.common.utils.ContextUtil;
import com.gedoumi.crowd.dataobj.model.User;
import com.gedoumi.crowd.dataobj.model.UserPointDetail;
import com.gedoumi.crowd.dataobj.vo.ResponseObject;
import com.gedoumi.crowd.dataobj.vo.api.UserPointVO;
import com.gedoumi.crowd.service.UserPointDetailService;
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
 * 积分Controller
 *
 * @author Minced
 */
@RequestMapping("/api/point")
@RestController
public class ApiPointController {

    @Resource
    private UserPointDetailService userPointDetailService;

    /**
     * 获取总参与人数
     *
     * @return ResponseObject
     */
    @GetMapping("/total")
    public ResponseObject totalPoint() {
        return ResponseObject.setSuccessResponse(userPointDetailService.getTotalPointedAndTotalUser());
    }

    /**
     * 获取用户当前积分
     *
     * @return ResponseObject
     */
    @GetMapping("/get")
    public ResponseObject userPoint() {
        User user = ContextUtil.getUserFromRequest();
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
        User user = ContextUtil.getUserFromRequest();
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
        User user = ContextUtil.getUserFromRequest();
        List<UserPointDetail> userPointDetailList = userPointDetailService.getUserPointDetailList(user.getId());
        // 封装返回数据
        return ResponseObject.setSuccessResponse(userPointDetailList.stream().map(userPointDetail -> {
            UserPointVO userPointVO = new UserPointVO();
            BeanUtils.copyProperties(userPointDetail, userPointVO);
            return userPointVO;
        }).collect(Collectors.toList()));
    }

}
