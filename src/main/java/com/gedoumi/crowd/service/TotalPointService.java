package com.gedoumi.crowd.service;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.dao.TotalPointDao;
import com.gedoumi.crowd.dataobj.model.TotalPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 总分数Service
 *
 * @author Minced
 */
@Service
public class TotalPointService {

    @Resource
    private TotalPointDao totalPointDao;

    /**
     * 获取总积分
     *
     * @return 总积分
     */
    public Long getTotalPoint() {
        TotalPoint totalPoint = totalPointDao.findById(1L).orElseThrow(() -> new TgException(INTERNAL_SERVER_ERROR, null));
        return totalPoint.getTotalPoint();
    }

    /**
     * 增加总积分
     *
     * @param point 增加的分数量
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void updateTotalPoint(Long point) {
        TotalPoint totalPoint = totalPointDao.findById(1L).orElseThrow(() -> new TgException(INTERNAL_SERVER_ERROR, null));
        totalPoint.setTotalPoint(totalPoint.getTotalPoint() + point);
        totalPointDao.save(totalPoint);
    }

}
