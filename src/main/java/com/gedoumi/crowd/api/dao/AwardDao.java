package com.gedoumi.crowd.api.dao;

import com.gedoumi.crowd.api.dataobj.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * 奖品Dao
 *
 * @author Minced
 */
public interface AwardDao extends JpaRepository<Award, Long> {

    /**
     * 根据奖品类型查询
     *
     * @param awardType 奖品类型
     * @return Optional
     */
    List<Award> findByAwardType(Integer awardType);

    /**
     * 根据奖品类型和概率查询在指定范围内的奖品
     *
     * @param awardType 奖品类型
     * @param pro       概率
     * @return 奖品
     */
    @Query(value = "select* from award where award_type = ?1 and award_stock > 0 and award_begin <= ?2 and award_end >= ?2", nativeQuery = true)
    Award findByAwardTypeAndAwardBeginAndAwardEnd(Integer awardType, BigDecimal pro);

}
