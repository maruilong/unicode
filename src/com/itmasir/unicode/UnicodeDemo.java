package com.itmasir.unicode;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @ClassName: UnicodeDemo
 * @Description:
 * @author shinian
 * @date 2017年7月21日 上午10:25:36
 * 
 */
public class UnicodeDemo {

    /**
     * UTF-8和GBK使用的都是Unicode字符集,但是他们的编码方式不同
     */
    private static final String UTF8 = "utf-8";
    private static final String GBK = "GBK";

    public static void main(String[] args) {
        String text = new String("吗");
        byte[] bytes = null;
        try {
            bytes = text.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (byte b : bytes) {
            System.out.println(b);
        }
    }
}
