package com.gedoumi.tg.dao;

import com.gedoumi.tg.TgApplicationTests;
import com.gedoumi.tg.dataobj.model.Award;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class AwardDaoTest extends TgApplicationTests {

    @Resource
    private AwardDao awardDao;

    @Test
    public void findByAwardType() {

    }

}