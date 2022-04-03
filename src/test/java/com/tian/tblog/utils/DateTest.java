package com.tian.tblog.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTest {
    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.minusDays(3);
        System.out.println(localDateTime.toInstant(ZoneOffset.UTC));
        long l = localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        System.out.println(l);
    }
}
