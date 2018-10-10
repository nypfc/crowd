package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.dataobj.model.UserPointDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 积分详情（明细）Dao
 *
 * @author Minced
 */
public interface UserPointDetailDao extends JpaRepository<UserPointDetail, Long> {

    /**
     * 查询参与助力的总用户数量
     *
     * @return 总用户数量
     */
    @Query(value = "select count(0) from (select count(0) from user_point_detail group by user_id) total_user", nativeQuery = true)
    Long countAllTotalUser();

    /**
     * 查询当日助力数量
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param userId    用户ID
     * @return 查询结果数量
     */
    Integer countByCreateTimeBetweenAndUserId(Date startTime, Date endTime, Long userId);

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 积分明细集合
     */
    List<UserPointDetail> findByUserIdOrderByCreateTimeDesc(Long userId);

}
