package com.trademark.cms.dao.vo;

import com.trademark.cms.dao.entity.CmsAdminBase;
import lombok.Data;

import java.io.Serializable;

@Data
public class CmsAdminBaseVo extends CmsAdminBase implements Serializable {

    /**
     * 是否为超级管理员
     */
    private Boolean isSuperAdmin;

    /**
     * 是否为管理员
     */
    private Boolean isAdmin;

    /**
     * 登录sessionId
     */
    private String token;

    private String test;

}