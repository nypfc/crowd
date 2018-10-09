package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.CrowdApplicationTests;
import com.gedoumi.crowd.dataobj.model.Award;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class AwardDaoTest extends CrowdApplicationTests {

    @Resource
    private AwardDao awardDao;

    @Test
    public void findByAwardType() {
        long sum = awardDao.findByAwardType(2).stream().mapToLong(Award::getAwardStock).sum();
        System.out.println(sum);
    }

    @Test
    public void findByAwardTypeAndAwardBeginGreaterThanAndAwardEndLessThan() {
        double random = Math.random();
        System.out.println(random);
        BigDecimal pro = new BigDecimal(random).setScale(4, BigDecimal.ROUND_DOWN);
        System.out.println(pro);
        Award award = awardDao.findByAwardTypeAndAwardBeginAndAwardEnd(1, pro);
        System.out.println(award);
    }

}