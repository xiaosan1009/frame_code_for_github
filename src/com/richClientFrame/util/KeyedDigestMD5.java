
package com.richClientFrame.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KeyedDigestMD5 {

    public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buffer);
            return md5.digest(key);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String getKeyedDigestSendSms(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("GBK"));

            String result = "";
            byte[] temp;
            temp = md5.digest();

            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String getKeyedDigest(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("GBK"));

            String result = "";
            byte[] temp;
            temp = md5.digest(key.getBytes("GBK"));
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }

            return result;

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getKeyedDigestUTF8(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF8"));

            String result = "";
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF8"));
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }

            return result;

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String mi;
        String s = "iloveyou";
        mi = getKeyedDigest(s, "cn.com.beyond.think.pet");

        System.out.println(mi);

        String t = getKeyedDigestUTF8(s, "");
        System.out.println(t);
        System.out.println(System.currentTimeMillis());

    }

}
