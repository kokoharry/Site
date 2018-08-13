package com.kokoharry.site.system.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 菜单角色关系
 */
public class RoleMenuRelation extends BaseDomain implements Serializable{

    /**
     * 
     */
    private String roleCode;

    /**
     * 
     */
    private String menuCode;

    /**
     * 操作权限
     */
    private int operationAuthority;

    /**
     * 创建方式 0 默认 数据库
     */
    private int createType;

    /**
     * 更新方式 默认0 数据库
     */
    private int updateType;


    /**
     *  取值方法get
     * @return roleCode
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     *  賦值方法set
     * @param roleCode
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     *  取值方法get
     * @return menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     *  賦值方法set
     * @param menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 操作权限 取值方法get
     * @return operationAuthority
     */
    public int getOperationAuthority() {
        return operationAuthority;
    }

    /**
     * 操作权限 賦值方法set
     * @param operationAuthority
     */
    public void setOperationAuthority(int operationAuthority) {
        this.operationAuthority = operationAuthority;
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}