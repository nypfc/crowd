package com.gedoumi.tg.dao;

import com.gedoumi.tg.dataobj.model.UserAwardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户抽奖详情（明细）Dao
 *
 * @author Minced
 */
public interface UserAwardDetailDao extends JpaRepository<UserAwardDetail, Long> {

    /**
     * 根据用户ID与抽奖类型查询
     *
     * @param userId 用户ID
     * @param type   抽奖类型
     * @return 查询结果数量
     */
    Integer countByUserIdAndAwardType(Long userId, Integer type);

}
