package com.trademark.cms.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "cms_user_base")
public class CmsUserBase implements Serializable {
    /**
     * 用户表主键
     */
    @Id
    @Column(name = "userole_id")
    private Integer userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户登录账号
     */
    @Column(name = "user_account")
    private String userAccount;

    /**
     * 用户登录密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 用户手机号
     */
    @Column(name = "user_mobile")
    private String userMobile;

    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 用户头像
     */
    @Column(name = "user_portrait")
    private String userPortrait;

    /**
     * 是否正常:0-正常;1-删除
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

    /**
     * 获取用户表主键
     *
     * @return userole_id - 用户表主键
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户表主键
     *
     * @param userId 用户表主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户登录账号
     *
     * @return user_account - 用户登录账号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户登录账号
     *
     * @param userAccount 用户登录账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     * 获取用户登录密码
     *
     * @return user_password - 用户登录密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户登录密码
     *
     * @param userPassword 用户登录密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 获取用户手机号
     *
     * @return user_mobile - 用户手机号
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * 设置用户手机号
     *
     * @param userMobile 用户手机号
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     * 获取用户邮箱
     *
     * @return user_email - 用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置用户邮箱
     *
     * @param userEmail 用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * 获取用户头像
     *
     * @return user_portrait - 用户头像
     */
    public String getUserPortrait() {
        return userPortrait;
    }

    /**
     * 设置用户头像
     *
     * @param userPortrait 用户头像
     */
    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait == null ? null : userPortrait.trim();
    }

    /**
     * 获取是否正常:0-正常;1-删除
     *
     * @return is_deleted - 是否正常:0-正常;1-删除
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否正常:0-正常;1-删除
     *
     * @param isDeleted 是否正常:0-正常;1-删除
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取修改人
     *
     * @return modify_id - 修改人
     */
    public Integer getModifyId() {
        return modifyId;
    }

    /**
     * 设置修改人
     *
     * @param modifyId 修改人
     */
    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userMobile=").append(userMobile);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userPortrait=").append(userPortrait);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", modifyId=").append(modifyId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}