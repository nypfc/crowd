package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.CrowdApplicationTests;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Component
public class UserPointDetailDaoTest extends CrowdApplicationTests {

    @Resource
    private UserPointDetailDao userPointDetailDao;

    @Test
    public void countByTotalUser() {
        Long count = userPointDetailDao.countByTotalUser();
        System.out.println(count);
    }

}