package com.tian.tblog.utils;

import org.junit.jupiter.api.Test;

public class UUIDTest {
    @Test
    public void test1(){
        String[] uuids = UUIDGenerator.getUUID(10);

        for (String uuid : uuids) {
            System.out.println(uuid);
        }
    }

    @Test
    public void test2(){
        System.out.println(UUIDGenerator.getShortUUID());
    }

    @Test
    public void test3(){
        String[] uuids = UUIDGenerator.getShortUUID(5);
        for (String uuid : uuids) {
            System.out.println(uuid);
        }
    }
}
