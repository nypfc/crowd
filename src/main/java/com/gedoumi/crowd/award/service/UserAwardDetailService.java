package com.gedoumi.crowd.award.service;

import com.gedoumi.crowd.award.dataobj.model.UserAwardDetail;
import com.gedoumi.crowd.award.mapper.ApiAwardMapper;
import com.gedoumi.crowd.common.exception.CrowdException;
import com.gedoumi.crowd.common.utils.ContextUtil;
import com.gedoumi.crowd.user.dataobj.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.ALREADY_AWARDED;
import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.NOT_ENOUGH_POINT;
import static com.gedoumi.crowd.common.enums.TreasureTypeEnum.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 用户抽奖详情（明细）Service
 *
 * @author Minced
 */
@Slf4j
@Service
public class UserAwardDetailService {

    @Resource
    private ApiAwardMapper apiAwardMapper;
    @Resource
    private AwardService awardService;

    /**
     * 创建用户抽奖明细
     *
     * @return 抽奖结果，0:未中奖，1:中奖
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer createDetail() {
        // 1.判断抽奖等级
        User user = ContextUtil.getUserFromRequest();
        Long point = user.getPoint();
        int awardType;
        if (0L <= point && point < AWARD_TYPE_1.getPoint())
            throw new CrowdException(BAD_REQUEST, NOT_ENOUGH_POINT);
        else if (AWARD_TYPE_1.getPoint() <= point && point < AWARD_TYPE_2.getPoint())
            awardType = AWARD_TYPE_1.getType();
        else if (AWARD_TYPE_2.getPoint() <= point && point < AWARD_TYPE_3.getPoint())
            awardType = AWARD_TYPE_2.getType();
        else if (AWARD_TYPE_3.getPoint() <= point && point < AWARD_TYPE_4.getPoint())
            awardType = AWARD_TYPE_3.getType();
        else if (AWARD_TYPE_4.getPoint() <= point && point < AWARD_TYPE_5.getPoint())
            awardType = AWARD_TYPE_4.getType();
        else
            awardType = AWARD_TYPE_5.getType();
        // 查询数据库已经有相同的抽奖详情（是否重复抽奖）
        if (apiAwardMapper.countAwarded(user.getId(), awardType) != 0) {
            log.warn("userId:{}, awardType:{}, 重复抽奖", user.getId(), awardType);
            throw new CrowdException(BAD_REQUEST, ALREADY_AWARDED);
        }

        // 2.抽奖并获取结果
        Integer result = awardService.raffle(user.getNickname(), awardType);

        // 3.创建抽奖详情（明细）
        UserAwardDetail awardDetail = new UserAwardDetail();
        awardDetail.setUserId(user.getId());
        awardDetail.setAwardType(awardType);
        awardDetail.setIsSuccess(result);
        apiAwardMapper.createUserAwardDetail(awardDetail);

        // 4.返回抽奖结果
        return result;
    }

    /**
     * 获取用户抽奖详情列表
     *
     * @param page 当前页码
     * @param rows 每页数据量
     * @return 用户抽奖详情集合
     */
    public List<UserAwardDetail> getUserAwardList(Integer page, Integer rows) {
        User user = ContextUtil.getUserFromRequest();
        PageHelper.startPage(page, rows);
        PageInfo<UserAwardDetail> pageInfo = new PageInfo<>(apiAwardMapper.queryUserAwardDetalList(user.getId()));
        return pageInfo.getList();
    }

}
