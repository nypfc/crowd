package com.gedoumi.crowd.point.controller;

import com.gedoumi.crowd.common.utils.ContextUtil;
import com.gedoumi.crowd.common.utils.ResponseObject;
import com.gedoumi.crowd.point.dataobj.model.UserPointDetail;
import com.gedoumi.crowd.point.dataobj.vo.UserPointVO;
import com.gedoumi.crowd.point.service.PointService;
import com.gedoumi.crowd.user.dataobj.model.User;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
    private PointService pointService;

    /**
     * 获取总参与人数
     *
     * @return ResponseObject
     */
    @GetMapping("/total")
    public ResponseObject totalPoint() {
        return ResponseObject.setSuccessResponse(pointService.getTotalPointedAndTotalUser());
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
        pointService.addPoint();
        return ResponseObject.setSuccessResponse();
    }

    /**
     * 用户助力积分明细
     *
     * @param page 当前页码
     * @param rows 每页数据量
     * @return ResponseObject
     */
    @GetMapping("/list/{page}/{rows}")
    public ResponseObject userPointList(@PathVariable Integer page, @PathVariable Integer rows) {
        List<UserPointDetail> userPointDetailList = pointService.getUserPointDetailList(page, rows);
        return ResponseObject.setSuccessResponse(userPointDetailList.stream().map(userPointDetail -> {
            UserPointVO userPointVO = new UserPointVO();
            BeanUtils.copyProperties(userPointDetail, userPointVO);
            return userPointVO;
        }).collect(Collectors.toList()));
    }

}
