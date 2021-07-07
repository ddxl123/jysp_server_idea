package com.example.demo.config.passwordconfig;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class Passworder {

    final String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+=,.;";
    final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    /**
     * 生成随机密码。
     *
     * @param length   密码长度。
     * @param isEncode 是否进行加密。
     * @return 如果 isEncode=true, 则返回加密过的密码；如果 isEncode=false，则返回未加密的密码。返回的加密过的密码长度为 60。
     */
    public String generateRandomPassword(int length, boolean isEncode) {
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        int charsLength = chars.length();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(chars.charAt(random.nextInt(charsLength)));
        }
        if (isEncode) {
            return bCryptPasswordEncoder.encode(stringBuffer.toString());
        } else {
            return stringBuffer.toString();
        }
    }
}
