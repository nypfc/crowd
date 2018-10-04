package com.gedoumi.tg.service;

import com.gedoumi.tg.dataobj.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户积分Service
 *
 * @author Minced
 */
@Service
public class UserPointService {

    /**
     * 每日积分助力
     *
     * @param user 用户对象
     */
    @Transactional
    public void addPoint(User user) {

    }

}
