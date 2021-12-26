package com.tian.tblog.utils;

import org.junit.jupiter.api.Test;

public class EncryptorTest {

    @Test
    public void test1(){
        System.out.println(EncryptorUtils.encodeText( "tian", "986132"));
        System.out.println(EncryptorUtils.decodeText( "pir2FJ9/f3iVkKmAY6MpWw==", "986132"));
        System.out.println(EncryptorUtils.encodeText("zt536428", "986132"));
    }
}
