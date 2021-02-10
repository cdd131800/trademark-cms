package com.trademark.cms.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "cms_menu_base")
public class CmsMenuBase implements Serializable {
    /**
     * 菜单主键
     */
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 菜单主键
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单编码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单地址
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 菜单类型:0-菜单;1-按钮
     */
    @Column(name = "menu_type")
    private Byte menuType;

    /**
     * 菜单描述
     */
    @Column(name = "menu_description")
    private String menuDescription;

    /**
     * 菜单上级
     */
    @Column(name = "menu_parent_id")
    private Integer menuParentId;

    /**
     * 排序字段
     */
    @Column(name = "menu_sort")
    private Integer menuSort;

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
     * 获取菜单主键
     *
     * @return menu_id - 菜单主键
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单主键
     *
     * @param menuId 菜单主键
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取菜单主键
     *
     * @return menu_name - 菜单主键
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单主键
     *
     * @param menuName 菜单主键
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单编码
     *
     * @return menu_code - 菜单编码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编码
     *
     * @param menuCode 菜单编码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取菜单地址
     *
     * @return menu_url - 菜单地址
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单地址
     *
     * @param menuUrl 菜单地址
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 获取菜单类型:0-菜单;1-按钮
     *
     * @return menu_type - 菜单类型:0-菜单;1-按钮
     */
    public Byte getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型:0-菜单;1-按钮
     *
     * @param menuType 菜单类型:0-菜单;1-按钮
     */
    public void setMenuType(Byte menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取菜单描述
     *
     * @return menu_description - 菜单描述
     */
    public String getMenuDescription() {
        return menuDescription;
    }

    /**
     * 设置菜单描述
     *
     * @param menuDescription 菜单描述
     */
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription == null ? null : menuDescription.trim();
    }

    /**
     * 获取菜单上级
     *
     * @return menu_parent_id - 菜单上级
     */
    public Integer getMenuParentId() {
        return menuParentId;
    }

    /**
     * 设置菜单上级
     *
     * @param menuParentId 菜单上级
     */
    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    /**
     * 获取排序字段
     *
     * @return menu_sort - 排序字段
     */
    public Integer getMenuSort() {
        return menuSort;
    }

    /**
     * 设置排序字段
     *
     * @param menuSort 排序字段
     */
    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
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
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", menuType=").append(menuType);
        sb.append(", menuDescription=").append(menuDescription);
        sb.append(", menuParentId=").append(menuParentId);
        sb.append(", menuSort=").append(menuSort);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", modifyId=").append(modifyId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}