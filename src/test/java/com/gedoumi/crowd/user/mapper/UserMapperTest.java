package com.gedoumi.crowd.user.mapper;

import com.gedoumi.crowd.CrowdApplicationTests;
import com.gedoumi.crowd.user.dataobj.model.User;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.Assert.*;

@Component
public class UserMapperTest extends CrowdApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void queryByToken() {
        User user = userMapper.queryByToken("aaaa");
        System.out.println(user);
    }

}