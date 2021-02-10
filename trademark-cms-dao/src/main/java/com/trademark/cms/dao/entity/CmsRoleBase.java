package com.trademark.cms.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "cms_role_base")
public class CmsRoleBase implements Serializable {
    /**
     * 角色主键
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 角色主键
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色key
     */
    @Column(name = "role_key")
    private String roleKey;

    /**
     * 角色描述
     */
    @Column(name = "role_description")
    private String roleDescription;

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
     * 获取角色主键
     *
     * @return role_id - 角色主键
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色主键
     *
     * @param roleId 角色主键
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色主键
     *
     * @return role_name - 角色主键
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色主键
     *
     * @param roleName 角色主键
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取角色key
     *
     * @return role_key - 角色key
     */
    public String getRoleKey() {
        return roleKey;
    }

    /**
     * 设置角色key
     *
     * @param roleKey 角色key
     */
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey == null ? null : roleKey.trim();
    }

    /**
     * 获取角色描述
     *
     * @return role_description - 角色描述
     */
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * 设置角色描述
     *
     * @param roleDescription 角色描述
     */
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
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
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleKey=").append(roleKey);
        sb.append(", roleDescription=").append(roleDescription);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", modifyId=").append(modifyId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}