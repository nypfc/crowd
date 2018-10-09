package com.gedoumi.tg.dao;

import com.gedoumi.tg.TgApplicationTests;
import com.gedoumi.tg.dataobj.model.User;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class UserDaoTest extends TgApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    public void findByToken() {
        Optional<User> user = userDao.findByToken("aaaa");
        System.out.println(user);
    }

}