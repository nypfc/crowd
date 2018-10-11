package com.gedoumi.crowd.award.mapper;

import com.gedoumi.crowd.award.dataobj.model.Award;
import com.gedoumi.crowd.award.dataobj.model.UserAwardDetail;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 奖品Mapper
 *
 * @author Minced
 */
@Mapper
public interface ApiAwardMapper {

    /**
     * 查询同种奖品抽奖次数
     *
     * @param userId    用户ID
     * @param awardType 奖品类型
     * @return 查询结果数量
     */
    Integer countAwarded(Long userId, Integer awardType);

    /**
     * 查询奖品库存
     *
     * @param awardType 奖品类型
     * @return 库存集合
     */
    Integer queryAwardStock(Integer awardType);

    /**
     * 抽奖
     *
     * @param awardType  奖品类型
     * @param bigDecimal 随机数
     * @return 奖品对象
     */
    Award raffle(Integer awardType, BigDecimal bigDecimal);

    /**
     * 更新库存（库存减少1）
     * @param awardId 奖品ID
     */
    void updateStock(Long awardId);

    /**
     * 创建用户获奖明细
     *
     * @param userAwardDetail 明细对象
     */
    void createUserAwardDetail(UserAwardDetail userAwardDetail);

    /**
     * 查询用户抽奖明细
     *
     * @param userId 用户ID
     * @return 抽奖明细对象
     */
    List<UserAwardDetail> queryUserAwardDetalList(Long userId);

}
