package com.gedoumi.tg.dao;

import com.gedoumi.tg.dataobj.model.UserPointDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * 积分详情（明细）Dao
 *
 * @author Minced
 */
public interface UserPointDetailDao extends JpaRepository<UserPointDetail, Long> {

    /**
     * 查询当日助力数量
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param userId    用户ID
     * @return 查询结果数量
     */
    Integer countByCreateTimeBetweenAndUserId(Date startTime, Date endTime, Long userId);

}
