package com.trademark.cms.controller.shiro;



/**
 * @Description: 管理员相关静态变量
 * created by andy on 2019/7/30
 **/
public class CmsShiroConstant {
    /**
     * 当前登录管理员
     */
    public static final String CURRENT_ADMIN = "CURRENT_ADMIN_INFO";
    /**
     * 当前管理员权限列表
     */
    public static final String CURRENT_ADMIN_PERMISSIONS = "CURRENT_ADMIN_PERMISSIONS";


    /**
     * 验证码获取路径
     */
    public static final String CAPTCHA_KAPTCHA_PATH = "/captcha/kaptcha.jpg";

    public static final String SHIRO_LOGIN_FAILURE = "shiroLoginFailure";

    /**
     * 角色，或者管理员的超级管理员名称
     */
    public static final String SUPER_ADMIN_NAME = "super_admin";
}
