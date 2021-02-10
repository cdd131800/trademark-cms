package com.trademark.cms.common.utils;

/**
 * 密码工具类
 */
public class CmsPasswordUtil {

    public static String encrypt(String password) {
        return CmsMD5Util.MD5(CmsBase64Util.encode(password));
    }
}
