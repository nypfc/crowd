package com.gedoumi.crowd.award.service;

import com.gedoumi.crowd.award.mapper.ApiAwardMapper;
import com.gedoumi.crowd.common.exception.CrowdException;
import com.gedoumi.crowd.component.WebSocketHandler;
import com.gedoumi.crowd.award.dataobj.model.Award;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.NOT_ENOUGH_STOCK;
import static java.math.BigDecimal.ROUND_DOWN;
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
    private ApiAwardMapper apiAwardMapper;

    @Resource
    private WebSocketHandler webSocketHandler;

    /**
     * 抽奖
     * 若中奖则扣除库存
     * 未中奖直接返回
     *
     * @param nickname  用户昵称
     * @param awardType 奖品类型
     * @return 抽奖结果，0:未中奖，1:中奖
     */
    Integer raffle(String nickname, Integer awardType) {
        // 1.查询对应奖品的库存
        if (apiAwardMapper.queryAwardStock(awardType) <= 0) {
            log.warn("{}号宝箱，库存不足", awardType);
            throw new CrowdException(BAD_REQUEST, NOT_ENOUGH_STOCK);
        }

        // 2.抽奖
        BigDecimal probability = new BigDecimal(Math.random()).setScale(4, ROUND_DOWN);
        log.debug("probability:{}", probability);
        Award award = apiAwardMapper.raffle(awardType, probability);
        // 若未中奖直接返回0
        if (apiAwardMapper.raffle(awardType, probability) == null) return 0;

        // 3.中奖扣除库存
        if (award.getAwardStock() - 1 < 0) {
            log.warn("奖品ID:{}，库存不足", award.getId());
            throw new CrowdException(BAD_REQUEST, NOT_ENOUGH_STOCK);
        }
        apiAwardMapper.updateStock(award.getId());

        // 4.推送中奖信息
        webSocketHandler.awardedMessage(nickname, awardType);

        return 1;
    }

}
