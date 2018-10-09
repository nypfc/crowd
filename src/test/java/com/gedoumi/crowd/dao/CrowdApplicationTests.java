package com.gedoumi.crowd.dao;

import com.gedoumi.crowd.CrowdApplicationTests;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class CrowdApplicationTests extends CrowdApplicationTests {

    @Resource
    private UserPointDetailDao userPointDetailDao;

    @Test
    public void countByCreateTimeBetween() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(sdf.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        Integer integer = userPointDetailDao.countByCreateTimeBetweenAndUserId(date, calendar.getTime(), 2L);
        System.out.println(integer);
    }
}