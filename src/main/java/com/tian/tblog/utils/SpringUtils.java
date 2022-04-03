package com.tian.tblog.utils;

import org.springframework.context.ApplicationContext;

public class SpringUtils {
    private static ApplicationContext context;

    public static ApplicationContext getIoc(){
        return context;
    }
    public static void setIoc(ApplicationContext context){
        SpringUtils.context = context;
    }
}
