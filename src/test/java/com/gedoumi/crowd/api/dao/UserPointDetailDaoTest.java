package com.gedoumi.crowd.api.dao;

import com.gedoumi.crowd.CrowdApplicationTests;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserPointDetailDaoTest extends CrowdApplicationTests {

    @Resource
    private UserPointDetailDao userPointDetailDao;

    @Test
    public void countAllTotalUser() {
        Long count = userPointDetailDao.countAllTotalUser();
        System.out.println(count);
    }

}