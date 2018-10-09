package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.dataobj.model.TotalPoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 总积分Dao
 *
 * @author Minced
 */
public interface TotalPointDao extends JpaRepository<TotalPoint, Long> {
}
