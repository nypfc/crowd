package com.gedoumi.crowd.point.mapper;

import com.gedoumi.crowd.point.dataobj.model.UserPointDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 用户积分Mapper
 *
 * @author Minced
 */
@Mapper
public interface ApiPointMapper {

    /**
     * 查询总积分
     *
     * @param id ID
     * @return 总积分
     */
    Long queryTotalPoint(Long id);

    /**
     * 查询参与过积分助力的用户数量
     *
     * @return 查询结果数量
     */
    Long countPointedUser();

    /**
     * 查询当天每日助力的数量
     *
     * @param userId    用户ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param type      固定为每日助力
     * @return 查询结果数量
     */
    Integer countPointedSameday(Long userId, Date startTime, Date endTime, Integer type);

    /**
     * 创建用户积分明细
     *
     * @param userPointDetail 用户积分明细
     */
    void createUserPointDetail(UserPointDetail userPointDetail);

    /**
     * 更新总积分
     *
     * @param id    ID
     * @param point 增加的积分量
     */
    void updateTotalPoint(Long id, Long point);

    /**
     * 查询用户积分明细
     *
     * @param userId 用户ID
     * @return 用户积分明细列表
     */
    List<UserPointDetail> queryUserPointDetailList(Long userId);

}
