package com.gedoumi.tg.dao;

import com.gedoumi.tg.TgApplicationTests;
import com.gedoumi.tg.dataobj.model.User;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDaoTest extends TgApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    public void countByToken() {
        Integer count = userDao.countByToken("aaaa");
        System.out.println(count);
    }

    @Test
    public void findByToken() {
        User user = userDao.findByToken("aaaa");
        System.out.println(user);
    }

}