package com.kokoharry.site.system.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 */
public class Menu extends BaseDomain implements Serializable{

    /**
     * 菜单编码
     */
    public String menuCode;

    /**
     * 菜单名称
     */
    public String menuName;

    /**
     * 菜单链接
     */
    public String menuHref;

    /**
     * 多级菜单父节点 无则为主节点
     */
    public String menuParentCode;

    /**
     * 菜单图标
     */
    public String menuIcon;

    /**
     * 创建方式 0 默认 数据库
     */
    public int createType;

    /**
     * 更新方式 默认0 数据库
     */
    public int updateType;

    private RoleMenuRelation roleMenuRelation;

    public List<Menu> menus;

    public RoleMenuRelation getRoleMenuRelation() {
        return roleMenuRelation;
    }

    public void setRoleMenuRelation(RoleMenuRelation roleMenuRelation) {
        this.roleMenuRelation = roleMenuRelation;
    }

    /**
     * 菜单编码 取值方法get
     * @return menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 菜单编码 賦值方法set
     * @param menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 菜单名称 取值方法get
     * @return menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名称 賦值方法set
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 菜单链接 取值方法get
     * @return menuHref
     */
    public String getMenuHref() {
        return menuHref;
    }

    /**
     * 菜单链接 賦值方法set
     * @param menuHref
     */
    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    /**
     * 多级菜单父节点 无则为主节点 取值方法get
     * @return menuParentCode
     */
    public String getMenuParentCode() {
        return menuParentCode;
    }

    /**
     * 多级菜单父节点 无则为主节点 賦值方法set
     * @param menuParentCode
     */
    public void setMenuParentCode(String menuParentCode) {
        this.menuParentCode = menuParentCode;
    }

    /**
     * 菜单图标 取值方法get
     * @return menuIcon
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 菜单图标 賦值方法set
     * @param menuIcon
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * 创建方式 0 默认 数据库 取值方法get
     * @return createType
     */
    public int getCreateType() {
        return createType;
    }

    /**
     * 创建方式 0 默认 数据库 賦值方法set
     * @param createType
     */
    public void setCreateType(int createType) {
        this.createType = createType;
    }

    /**
     * 更新方式 默认0 数据库 取值方法get
     * @return updateType
     */
    public int getUpdateType() {
        return updateType;
    }

    /**
     * 更新方式 默认0 数据库 賦值方法set
     * @param updateType
     */
    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}