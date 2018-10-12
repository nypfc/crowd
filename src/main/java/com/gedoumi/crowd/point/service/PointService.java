package com.gedoumi.crowd.point.service;

import com.gedoumi.crowd.common.enums.PointEnum;
import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.common.utils.ContextUtil;
import com.gedoumi.crowd.point.dataobj.model.UserPointDetail;
import com.gedoumi.crowd.point.dataobj.vo.TotalPointVO;
import com.gedoumi.crowd.point.mapper.ApiPointMapper;
import com.gedoumi.crowd.user.dataobj.dto.PointDTO;
import com.gedoumi.crowd.user.dataobj.model.User;
import com.gedoumi.crowd.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.ALREADY_POINTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 用户积分Service
 *
 * @author Minced
 */
@Service
public class PointService {

    @Resource
    private ApiPointMapper apiPointMapper;

    @Resource
    private UserService userService;

    /**
     * 获取参数过助力的总用户数
     *
     * @return 总用户数
     */
    public TotalPointVO getTotalPointedAndTotalUser() {
        User user = ContextUtil.getUserFromRequest();
        TotalPointVO totalPointVO = new TotalPointVO();
        totalPointVO.setUserPoint(user.getPoint());
        totalPointVO.setTotalPoint(apiPointMapper.queryTotalPoint(1L));
        totalPointVO.setTotalUser(apiPointMapper.countPointedUser());
        return totalPointVO;
    }

    /**
     * 每日积分助力
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void addPoint() {
        // 1.更新用户积分
        Long userId = ContextUtil.getUserFromRequest().getId();
        long point = PointEnum.DAY.getPoint();
        userService.updateUserPoint(userId, point);

        // 2.查询当日是否已经助力
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date startTime = null;
        try {
            startTime = sdf.parse(sdf.format(new Date()));
            calendar.setTime(startTime);
            calendar.add(Calendar.DATE, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer count = apiPointMapper.countPointedSameday(userId, startTime, calendar.getTime(), PointEnum.DAY.getType());
        if (count != 0) throw new TgException(BAD_REQUEST, ALREADY_POINTED);

        // 3.增加积分明细
        UserPointDetail userPointDetail = new UserPointDetail();
        userPointDetail.setUserId(userId);
        userPointDetail.setPoint(point);
        userPointDetail.setType(PointEnum.DAY.getType());
        apiPointMapper.createUserPointDetail(userPointDetail);

        // 4.更新总积分量
        apiPointMapper.updateTotalPoint(1L, point);
    }

    /**
     * 获取用户积分明细列表
     *
     * @param page 当前页码
     * @param rows 每页数据量
     * @return 积分明细集合
     */
    public List<UserPointDetail> getUserPointDetailList(Integer page, Integer rows) {
        User user = ContextUtil.getUserFromRequest();
        PageHelper.startPage(page, rows);
        PageInfo<UserPointDetail> pageInfo = new PageInfo<>(apiPointMapper.queryUserPointDetailList(user.getId()));
        return pageInfo.getList();
    }

    /**
     * 获取管理后台用户积分明细列表
     *
     * @param page 当前页
     * @param rows 每页数据量
     * @return PointDTO
     */
    public List<PointDTO> pointList(Integer page, Integer rows) {
//        // 1.分页查询积分详情列表
//        Page<UserPointDetail> pageList = userPointDetailDao.findAll(PageRequest.of(page - 1, rows));
//        List<UserPointDetail> userPointDetailList = pageList.getContent();
//
//        // 2.根据用户ID集合查询用户信息
//        List<Long> userIdList = userPointDetailList.stream().map(UserPointDetail::getUserId).collect(Collectors.toList());
//        List<User> userList = userService.getUserByIdList(userIdList);
//
//        // 3.组装数据
//        ArrayList<PointDTO> list = Lists.newArrayList();
//        detail:
//        for (UserPointDetail userPointDetail : userPointDetailList) {
//            for (User user : userList) {
//                if (userPointDetail.getUserId().equals(user.getId())) {
//                    PointDTO pointDTO = new PointDTO();
//                    pointDTO.setUser(user);
//                    pointDTO.setUserPointDetail(userPointDetail);
//                    list.add(pointDTO);
//                    continue detail;
//                }
//            }
//        }
        return null;
    }

}
