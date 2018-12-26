package com.pxkj.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordEncoder {

    private static String algorithmName = "md5";

    public static String encryptPassword(String password) {
        String newPassword = new SimpleHash(
                algorithmName,//加密算法
                password//密码
        ).toHex();
        return newPassword;
    }

}
