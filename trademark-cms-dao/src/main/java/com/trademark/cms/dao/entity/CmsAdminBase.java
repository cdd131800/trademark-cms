package com.trademark.cms.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "cms_admin_base")
public class CmsAdminBase {
    /**
     * 管理员主键
     */
    @Id
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 管理员登录账号
     */
    @Column(name = "admin_account")
    private String adminAccount;

    /**
     * 管理员登录密码
     */
    @Column(name = "admin_password")
    private String adminPassword;

    /**
     * 管理员名称
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 管理员手机号
     */
    @Column(name = "admin_mobile")
    private String adminMobile;

    /**
     * 管理员邮箱
     */
    @Column(name = "admin_email")
    private String adminEmail;

    /**
     * 描述
     */
    @Column(name = "admin_description")
    private String adminDescription;

    /**
     * 锁定状态：0-未锁定；1-已锁定
     */
    @Column(name = "is_lock")
    private Byte isLock;

    /**
     * 是否正常:0-正常;1-删除;
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 修改人
     */
    @Column(name = "modify_id")
    private Integer modifyId;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    private List<CmsRoleBase> roles;

    private List<CmsMenuBase> menus;

}