package com.gedoumi.crowd.service;

import com.gedoumi.crowd.common.enums.PointEnum;
import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.dao.UserPointDetailDao;
import com.gedoumi.crowd.dataobj.dto.PointDTO;
import com.gedoumi.crowd.dataobj.model.User;
import com.gedoumi.crowd.dataobj.model.UserPointDetail;
import com.gedoumi.crowd.dataobj.vo.api.TotalPointAndUserVO;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.ALREADY_POINTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 用户积分Service
 *
 * @author Minced
 */
@Service
public class UserPointDetailService {

    @Resource
    private UserPointDetailDao userPointDetailDao;

    @Resource
    private TotalPointService totalPointService;
    @Resource
    private UserService userService;

    /**
     * 获取参数过助力的总用户数
     *
     * @return 总用户数
     */
    public TotalPointAndUserVO getTotalPointedAndTotalUser() {
        TotalPointAndUserVO pointAndUserVO = new TotalPointAndUserVO();
        pointAndUserVO.setTotalPoint(totalPointService.getTotalPoint());
        pointAndUserVO.setTotalUser(userPointDetailDao.countAllTotalUser());
        return pointAndUserVO;
    }

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
        userService.updateUserPoint(user);

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
        totalPointService.updateTotalPoint(point);
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

    /**
     * 获取管理后台用户积分明细列表
     *
     * @param page 当前页
     * @param rows 每页数据量
     * @return PointDTO
     */
    public List<PointDTO> pointList(Integer page, Integer rows) {
        // 1.分页查询积分详情列表
        Page<UserPointDetail> pageList = userPointDetailDao.findAll(PageRequest.of(page - 1, rows));
        List<UserPointDetail> userPointDetailList = pageList.getContent();

        // 2.根据用户ID集合查询用户信息
        List<Long> userIdList = userPointDetailList.stream().map(UserPointDetail::getUserId).collect(Collectors.toList());
        List<User> userList = userService.getUserByIdList(userIdList);

        // 3.组装数据
        ArrayList<PointDTO> list = Lists.newArrayList();
        detail: for (UserPointDetail userPointDetail : userPointDetailList) {
            for (User user : userList) {
                if (userPointDetail.getUserId().equals(user.getId())) {
                    PointDTO pointDTO = new PointDTO();
                    pointDTO.setUser(user);
                    pointDTO.setUserPointDetail(userPointDetail);
                    list.add(pointDTO);
                    continue detail;
                }
            }
        }
        return list;
    }

}
