package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.CrowdApplicationTests;
import com.gedoumi.crowd.dataobj.model.User;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class UserDaoTest extends CrowdApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    public void findByToken() {
        Optional<User> user = userDao.findByToken("aaaa");
        System.out.println(user);
    }

}