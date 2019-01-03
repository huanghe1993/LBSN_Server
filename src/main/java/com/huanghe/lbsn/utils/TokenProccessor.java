package com.huanghe.lbsn.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Author huanghe
 * @Date 2018/12/27 16:36
 * @Description 生成Token的工具类：
 */
public class TokenProccessor {
    private TokenProccessor(){};
    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }

    /**
     * 生成Token Token：Nv6RRuGEVvmGjB+jimI/gw==
     *
     * @return
     */
    public String makeToken() { // checkException
        // 7346734837483 834u938493493849384 43434384
        String token = (System.currentTimeMillis() + new Random()
                .nextInt(999999999)) + "";
        // 数据指纹 128位长 16个字节 md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            // base64编码--任意二进制编码明文字符 adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            String tokenId  = encoder.encode(md5);
            return  tokenId.replace("+", "H");//
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String tokenId=  TokenProccessor.getInstance().makeToken();
        System.out.println(tokenId);
    }

}
