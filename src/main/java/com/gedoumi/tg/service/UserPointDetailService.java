package com.gedoumi.tg.service;

import com.gedoumi.tg.common.enums.PointEnum;
import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.TotalPointDao;
import com.gedoumi.tg.dao.UserDao;
import com.gedoumi.tg.dao.UserPointDetailDao;
import com.gedoumi.tg.dataobj.model.TotalPoint;
import com.gedoumi.tg.dataobj.model.User;
import com.gedoumi.tg.dataobj.model.UserPointDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.gedoumi.tg.common.constants.ResponseMessage.ALREADY_POINTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 用户积分Service
 *
 * @author Minced
 */
@Service
public class UserPointDetailService {

    @Resource
    private UserDao userDao;
    @Resource
    private UserPointDetailDao userPointDetailDao;
    @Resource
    private TotalPointDao totalPointDao;

    /**
     * 每日积分助力
     *
     * @param user 用户对象
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void addPoint(User user) {
        // 1.更新用户积分
        long point = PointEnum.DAY.getPoint();
        user.setPoint(user.getPoint() + point);
        userDao.save(user);

        // 2.查询当日是否已经助力
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(sdf.format(new Date()));
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer count = userPointDetailDao.countByCreateTimeBetweenAndUserId(date, calendar.getTime(), user.getId());
        if (count != 0) throw new TgException(BAD_REQUEST, ALREADY_POINTED);

        // 3.增加积分详情（明细）
        UserPointDetail userPointDetail = new UserPointDetail();
        userPointDetail.setUserId(user.getId());
        userPointDetail.setPoint(point);
        userPointDetail.setType(PointEnum.DAY.getType());
        userPointDetailDao.save(userPointDetail);

        // 4.更新总积分量
        TotalPoint totalPoint = totalPointDao.findById(1L).orElseThrow(() -> new TgException(INTERNAL_SERVER_ERROR, null));
        totalPoint.setTotalPoint(totalPoint.getTotalPoint() + point);
        totalPointDao.save(totalPoint);
    }

    /**
     * 获取用户积分明细列表
     *
     * @param userId 用户ID
     * @return 积分明细集合
     */
    public List<UserPointDetail> getUserPointDetailList(Long userId) {
        return userPointDetailDao.findByUserIdOrderByCreateTimeDesc(userId);
    }

}
