package com.cn.gazelle.logistics.util;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;

/**
 * Created by CYH on 2015/12/15.
 */
public class SHAUtil {

    /**
     * SHA-1消息摘要算法
     */
    public static final String encodeSHA(String string) throws Exception {
        //将字符串转换成字节数组
        byte[] data = string.getBytes();
        // 初始化MessageDigest,SHA即SHA-1的简称
        MessageDigest md = MessageDigest.getInstance("SHA");
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-256消息摘要算法
     */
    public static final String encodeSHA256(String string) throws Exception {
        //将字符串转换成字节数组
        byte[] data = string.getBytes();
        // 初始化MessageDigest,SHA即SHA-1的简称
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-384消息摘要算法
     */
    public static final String encodeSHA384(String string) throws Exception {
        //将字符串转换成字节数组
        byte[] data = string.getBytes();
        // 初始化MessageDigest,SHA即SHA-1的简称
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-512消息摘要算法
     */
    public static final String encodeSHA512(String string) throws Exception {
        //将字符串转换成字节数组
        byte[] data = string.getBytes();
        // 初始化MessageDigest,SHA即SHA-1的简称
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

}
