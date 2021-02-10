package com.trademark.cms.common.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsLoginDto implements Serializable {
    /**
     * 管理员登录账号
     */
    private String adminAccount;

    /**
     * 管理员登录密码
     */
    private String adminPassword;
    /**
     * 验证码
     */
    private String captchaCode;
}
