package com.gedoumi.tg.dao;

import com.gedoumi.tg.dataobj.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
