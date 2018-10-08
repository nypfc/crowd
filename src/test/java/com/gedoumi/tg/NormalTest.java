package com.gedoumi.tg;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NormalTest {

    @Test
    public void dateTest() {
        for (int a = 0; a < 100; a++) {
            int i = new Random().nextInt(10001);
            System.out.println(i);
        }
    }

}
