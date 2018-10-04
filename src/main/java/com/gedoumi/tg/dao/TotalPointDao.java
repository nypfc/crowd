package com.gedoumi.tg.dao;

import com.gedoumi.tg.dataobj.model.TotalPoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 总积分Dao
 *
 * @author Minced
 */
public interface TotalPointDao extends JpaRepository<TotalPoint, Long> {
}
