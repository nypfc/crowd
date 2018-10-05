package com.gedoumi.tg.dao;

import com.gedoumi.tg.TgApplicationTests;
import com.gedoumi.tg.dataobj.model.TotalPoint;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TotalPointDaoTest extends TgApplicationTests {

    @Autowired
    private TotalPointDao totalPointDao;

    @Test
    public void save() {
        TotalPoint totalPoint = new TotalPoint();
        totalPoint.setId(1L);
        totalPoint.setTotalPoint(6L);
        System.out.println(totalPoint);
        totalPointDao.save(totalPoint);
    }

    @Test
    public void findOne() {
        TotalPoint totalPoint = totalPointDao.findById(1L).orElseThrow(() -> new RuntimeException("我草泥马，为什么是空的"));
        System.out.println(totalPoint);
    }

}