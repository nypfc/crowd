package com.gedoumi.tg.controller;

import com.gedoumi.tg.dataobj.model.User;
import com.gedoumi.tg.dataobj.model.UserAwardDetail;
import com.gedoumi.tg.dataobj.vo.ResponseObject;
import com.gedoumi.tg.dataobj.vo.UserAwardVO;
import com.gedoumi.tg.service.UserAwardDetailService;
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
 * 抽奖Controller
 *
 * @author Minced
 */
@RequestMapping("/award")
@RestController
public class AwardController {

    @Resource
    private UserAwardDetailService userAwardDetailService;
    @Resource
    private UserService userService;

    /**
     * 用户抽奖
     *
     * @return ResponseObject
     */
    @PostMapping("/raffle")
    public ResponseObject raffle() {
        User user = userService.getUserFromRequest();
        HashMap<String, Integer> map = Maps.newHashMap();
        map.put("result", userAwardDetailService.create(user));
        return ResponseObject.setSuccessResponse(map);
    }

    /**
     * 用户抽奖列表
     *
     * @return ResponseObject
     */
    @GetMapping("/list")
    public ResponseObject userAwardList() {
        User user = userService.getUserFromRequest();
        List<UserAwardDetail> userAwardList = userAwardDetailService.getUserAwardList(user.getId());
        // 封装返回数据
        return ResponseObject.setSuccessResponse(userAwardList.stream().map(userAwardDetail -> {
            UserAwardVO userAwardVO = new UserAwardVO();
            BeanUtils.copyProperties(userAwardDetail, userAwardVO);
            return userAwardVO;
        }).collect(Collectors.toList()));
    }

}
