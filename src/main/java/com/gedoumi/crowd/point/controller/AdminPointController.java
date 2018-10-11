package com.gedoumi.crowd.point.controller;

import com.gedoumi.crowd.common.utils.ResponseObject;
import com.gedoumi.crowd.point.service.PointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 管理后台积分Controller
 *
 * @author Minced
 */
@RequestMapping("/admin/point")
@RestController
public class AdminPointController {

    @Resource
    private PointService pointService;

    /**
     * 用户积分明细列表
     *
     * @param page 当前页码
     * @param rows 每页数据量
     * @return ResponseObject
     */
    @GetMapping("/list")
    public ResponseObject pointList(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "30") Integer rows) {
        return ResponseObject.setSuccessResponse(pointService.pointList(page, rows));
    }

}
