package com.tian.tblog.utils;

import java.util.UUID;

public class UUIDGenerator {
    public UUIDGenerator() {}

    /**
     * 获得一个 UUID
     * @return String UUID
     */
    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉 “-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }

    /**
     * 获得一个长 8 位的短 UUID
     * @return String UUID
     */
    public static String getShortUUID(){
        return getUUID().substring(0, 8);
    }

    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的 UUID 数量
     * @return String[] UUID 数组
     */
    public static String[] getUUID(int number){
        if(number < 1) return null;
        String[] ss = new String[number];
        for(int i=0;i<number;i++) ss[i] = getUUID();
        return ss;
    }

    /**
    * 获得指定数量的短 UUID
    * @param number int 需要的短 UUID 数量
    * @return String[] 短 UUID 数组
    */
    public static String[] getShortUUID(int number){
        if(number < 1) return null;
        String[] ss = new String[number];
        for(int i=0;i<number;i++) ss[i] = getShortUUID();
        return ss;
    }
}
