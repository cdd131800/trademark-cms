package com.trademark.cms.common.utils;

import java.io.*;
import java.util.Base64;

/**
 * @author Miracle Luna
 * @version 1.0
 * @date 2019/7/3 18:55
 */
public class CmsBase64Util {

    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 文件转字符串
     *
     * @param file
     * @return
     */
    public static String file2String(File file) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = buffer.readLine()) != null) {
                sb.append(temp);

            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 给字符串加密
     *
     * @param text
     * @return
     */
    public static String encode(String text) {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }


    /**
     * 将加密后的字符串进行解密
     *
     * @param encodedText
     * @return
     */
    public static String decode(String encodedText) {
        String text = null;
        try {
            text = new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //YWRtaW4=
        String username = "admin";
        String password = "admin";

        // 加密
        System.out.println("====  [加密后] 用户名/密码  =====");
        System.out.println(CmsBase64Util.encode(username));
        System.out.println(CmsBase64Util.encode(password));


        // 解密
        System.out.println("\n====  [解密后] 用户名/密码  =====");
        System.out.println(CmsBase64Util.decode(CmsBase64Util.encode(username)));
        System.out.println(CmsBase64Util.decode(CmsBase64Util.encode(password)));
    }
}