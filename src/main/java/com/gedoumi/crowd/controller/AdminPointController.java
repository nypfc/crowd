package com.gedoumi.crowd.controller;

import com.gedoumi.crowd.dataobj.vo.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理后台积分Controller
 *
 * @author Minced
 */
@RequestMapping("/admin/point")
@RestController
public class AdminPointController {

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
        return null;
    }

}
