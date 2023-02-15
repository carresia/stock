package com.cienet.zheng.stock.common;

/**
 * @author zhengshan
 * @description
 * @create 2023/2/15
 */
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    private static ThreadLocal<MessageDigest> MESSAGE_DIGEST_LOCAL = new ThreadLocal<MessageDigest>() {
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var2) {
                return null;
            }
        }
    };
    private static final int HEX_VALUE_COUNT = 16;

    public Md5Utils() {
    }

    public static String getMD5(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = (MessageDigest)MESSAGE_DIGEST_LOCAL.get();
        if (messageDigest != null) {
            return (new BigInteger(1, messageDigest.digest(bytes))).toString(16);
        } else {
            throw new NoSuchAlgorithmException("MessageDigest get MD5 instance error");
        }
    }

    public static String getMD5(String value, String encode) {
        try {
            return getMD5(value.getBytes(encode));
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
}
