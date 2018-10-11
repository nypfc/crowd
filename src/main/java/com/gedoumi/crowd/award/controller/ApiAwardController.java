package com.gedoumi.crowd.award.controller;

import com.gedoumi.crowd.award.dataobj.model.UserAwardDetail;
import com.gedoumi.crowd.award.dataobj.vo.UserAwardVO;
import com.gedoumi.crowd.award.service.UserAwardDetailService;
import com.gedoumi.crowd.common.utils.ResponseObject;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽奖Controller
 *
 * @author Minced
 */
@RequestMapping("/api/award")
@RestController
public class ApiAwardController {

    @Resource
    private UserAwardDetailService userAwardDetailService;

    /**
     * 用户抽奖
     *
     * @return ResponseObject
     */
    @PostMapping("/raffle")
    public ResponseObject raffle() {
        HashMap<String, Integer> map = Maps.newHashMap();
        map.put("result", userAwardDetailService.createDetail());
        return ResponseObject.setSuccessResponse(map);
    }

    /**
     * 用户抽奖列表
     *
     * @param page 当前页码
     * @param rows 每页数据量
     * @return ResponseObject
     */
    @GetMapping("/list/{page}/{rows}")
    public ResponseObject userAwardList(@PathVariable Integer page, @PathVariable Integer rows) {
        List<UserAwardDetail> userAwardList = userAwardDetailService.getUserAwardList(page, rows);
        return ResponseObject.setSuccessResponse(userAwardList.stream().map(userAwardDetail -> {
            UserAwardVO userAwardVO = new UserAwardVO();
            BeanUtils.copyProperties(userAwardDetail, userAwardVO);
            return userAwardVO;
        }).collect(Collectors.toList()));
    }

}
