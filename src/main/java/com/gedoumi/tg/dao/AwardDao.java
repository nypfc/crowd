package com.gedoumi.tg.dao;

import com.gedoumi.tg.dataobj.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 奖品Dao
 *
 * @author Minced
 */
public interface AwardDao extends JpaRepository<Award, Long> {
}
