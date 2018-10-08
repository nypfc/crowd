package com.gedoumi.tg.service;

import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.UserAwardDetailDao;
import com.gedoumi.tg.dataobj.model.User;
import com.gedoumi.tg.dataobj.model.UserAwardDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.gedoumi.tg.common.constants.ResponseMessage.ALREADY_AWARDED;
import static com.gedoumi.tg.common.constants.ResponseMessage.NOT_ENOUGH_POINT;
import static com.gedoumi.tg.common.enums.TreasureTypeEnum.*;
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

    /**
     * 创建用户抽奖详情
     *
     * @param user 用户
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void create(User user) {
        // 1.判断抽奖等级
        Long point = user.getPoint();
        int type;
        if (0L <= point && point < TYPE_1.getPoint()) {
            throw new TgException(BAD_REQUEST, NOT_ENOUGH_POINT);
        } else if (TYPE_1.getPoint() <= point && point < TYPE_2.getPoint()) {
            type = TYPE_1.getType();
        } else if (TYPE_2.getPoint() <= point && point < TYPE_3.getPoint()) {
            type = TYPE_2.getType();
        } else if (TYPE_3.getPoint() <= point && point < TYPE_4.getPoint()) {
            type = TYPE_3.getType();
        } else if (TYPE_4.getPoint() <= point && point < TYPE_5.getPoint()) {
            type = TYPE_4.getType();
        } else {
            type = TYPE_5.getType();
        }
        // 是否重复抽奖
        if (userAwardDetailDao.countByUserIdAndAwardType(user.getId(), type) != 0) {
            log.debug("userId:{}, type:{}, 重复抽奖");
            throw new TgException(BAD_REQUEST, ALREADY_AWARDED);
        }

        // TODO 2.抽奖
        int success = 0;

        // 3.减少库存


        // 4.创建抽奖明细
        UserAwardDetail awardDetail = new UserAwardDetail();
        awardDetail.setUserId(user.getId());
        awardDetail.setAwardType(type);
        awardDetail.setIsSuccess(success);
        userAwardDetailDao.save(awardDetail);

    }

    /**
     * 获取用户抽奖详情列表
     *
     * @param userId 用户ID
     * @return 用户抽奖详情集合
     */
    public List<UserAwardDetail> getUserAwardList(Long userId) {
        return userAwardDetailDao.findByUserId(userId);
    }

}
