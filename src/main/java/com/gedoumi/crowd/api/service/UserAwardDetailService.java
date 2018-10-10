package com.gedoumi.crowd.api.service;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.api.dao.UserAwardDetailDao;
import com.gedoumi.crowd.api.dataobj.model.User;
import com.gedoumi.crowd.api.dataobj.model.UserAwardDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.gedoumi.crowd.common.constants.ResponseMessage.ALREADY_AWARDED;
import static com.gedoumi.crowd.common.constants.ResponseMessage.NOT_ENOUGH_POINT;
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
    private UserAwardDetailDao userAwardDetailDao;
    @Resource
    private AwardService awardService;

    /**
     * 创建用户抽奖详情（明细）
     *
     * @param user 用户
     * @return 抽奖结果，0:未中奖，1:中奖
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer create(User user) {
        // 1.判断抽奖等级
        Long point = user.getPoint();
        int awardType;
        if (0L <= point && point < AWARD_TYPE_1.getPoint())
            throw new TgException(BAD_REQUEST, NOT_ENOUGH_POINT);
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
        if (userAwardDetailDao.countByUserIdAndAwardType(user.getId(), awardType) != 0) {
            log.warn("userId:{}, awardType:{}, 重复抽奖", user.getId(), awardType);
            throw new TgException(BAD_REQUEST, ALREADY_AWARDED);
        }

        // 2.抽奖并获取结果
        Integer result = awardService.raffle(user.getNickname(), awardType);

        // 3.创建抽奖详情（明细）
        UserAwardDetail awardDetail = new UserAwardDetail();
        awardDetail.setUserId(user.getId());
        awardDetail.setAwardType(awardType);
        awardDetail.setIsSuccess(result);
        userAwardDetailDao.save(awardDetail);

        // 4.返回抽奖结果
        return result;
    }

    /**
     * 获取用户抽奖详情列表
     *
     * @param userId 用户ID
     * @return 用户抽奖详情集合
     */
    public List<UserAwardDetail> getUserAwardList(Long userId) {
        return userAwardDetailDao.findByUserIdOrderByCreateTimeDesc(userId);
    }

}
