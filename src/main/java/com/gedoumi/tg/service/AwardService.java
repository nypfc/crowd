package com.gedoumi.tg.service;

import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.AwardDao;
import com.gedoumi.tg.dataobj.model.Award;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;
import java.util.Random;

import static com.gedoumi.tg.common.constants.ResponseMessage.AWARD_NOT_EXIST;
import static com.gedoumi.tg.common.enums.TreasureTypeEnum.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 奖品Service
 *
 * @author Minced
 */
@Slf4j
@Service
public class AwardService {

    @Resource
    private AwardDao awardDao;

    /**
     * 扣除库存
     *
     * @param awardId 奖品ID
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void deductStock(Long awardId) {
        Award award = awardDao.findById(awardId).orElseThrow(() -> {
            log.debug("awardID:{}，未查询到奖品", awardId);
            return new TgException(BAD_REQUEST, AWARD_NOT_EXIST);
        });
        award.setAwardStock(award.getAwardStock());
        awardDao.save(award);
    }

    /**
     * 抽奖
     * 若中奖则扣除库存
     * 未中奖直接返回
     *
     * @param awardType 奖品类型
     * @return 抽奖结果，0:未中奖，1:中奖
     */
    public Integer raffle(Integer awardType) {
        // TODO 1.抽奖
        List<Award> awardList = awardDao.findByAwardType(awardType);
        // 生成随机数
        int randomNum = new Random().nextInt(10001);
        // 判断数字与抽奖类型的中奖区间内
        if (awardType.equals(AWARD_TYPE_1.getType())) {

        } else if (awardType.equals(AWARD_TYPE_2.getType())) {

        } else if (awardType.equals(AWARD_TYPE_3.getType())) {

        } else if (awardType.equals(AWARD_TYPE_4.getType())) {

        } else if (awardType.equals(AWARD_TYPE_5.getType())) {

        } else {

        }

        if (false) {
            // 未中奖返回0
            return 0;
        }

        // 2.中奖扣除库存
//        award.setAwardStock(award.getAwardStock());
//        awardDao.save(award);
        return 1;
    }

}
