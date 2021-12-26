package com.tian.tblog.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class EncryptorUtils {
    private static final BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
    private static final BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
    private static String pwdKey = null;

    public static String encodeText(String content, String key){
        if(pwdKey == null){
            pwdKey = key;
            textEncryptor.setPassword(key);
        }
        return textEncryptor.encrypt(content);
    }

    public static String decodeText(String content, String key){
        if(pwdKey == null){
            pwdKey = key;
            textEncryptor.setPassword(key);
        }
        content = content.replace(" ", "+");
        return textEncryptor.decrypt(content);
    }

    public static String encodePassword(String password){
        return passwordEncryptor.encryptPassword(password);
    }

    public static Boolean checkPassword(String originPassword, String encodePassword){
        return passwordEncryptor.checkPassword(originPassword, encodePassword);
    }
}
