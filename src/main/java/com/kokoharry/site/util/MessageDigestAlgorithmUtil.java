package com.kokoharry.site.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by luyb on 2017/9/7.
 */
public class MessageDigestAlgorithmUtil {

    /**
     * 日志类
     */
    private static Logger logger = LogManager.getLogger(MessageDigestAlgorithmUtil.class);

    /**
     * 获取MD5加密
     *
     * @param pwd
     *            需要加密的字符串
     * @return String字符串 加密后的字符串
     */
    public static String getMD5Pwd(String pwd) {
        try {
            // 创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            // 调用加密对象的方法，加密的动作已经完成
            byte[] bs = digest.digest(pwd.getBytes());
            // 第一步，将数据全部转换成正数：
            String hexString = "";
            for (byte b : bs) {
                // 第一步，将数据全部转换成正数：
                int temp = b & 255;
                // 第二步，将所有的数据转换成16进制的形式
                if (temp < 16 && temp >= 0) {
                    // 手动补上一个“0”
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            logger.error("获取md5编码异常",e);
        }
        return "";
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String pwd = MessageDigestAlgorithmUtil.getMD5Pwd("423111lyb");
        System.out.println(pwd);
    }

}
