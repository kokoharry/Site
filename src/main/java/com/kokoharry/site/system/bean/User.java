package com.kokoharry.site.system.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 用户信息
 */
public class User extends BaseDomain implements Serializable{

    /**
     * 
     */
    private String roleCode;

    private String realName;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String telphone;

    /**
     * 用户等级
     */
    private int userLevel;

    /**
     * 创建方式 0数据库添加 默认 1网页添加
     */
    private int createType;

    /**
     * 更新方式 0数据库 1网页
     */
    private int updateType;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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
     * 用户姓名 取值方法get
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户姓名 賦值方法set
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码 取值方法get
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码 賦值方法set
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 电话 取值方法get
     * @return telphone
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 电话 賦值方法set
     * @param telphone
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * 用户等级 取值方法get
     * @return userLevel
     */
    public int getUserLevel() {
        return userLevel;
    }

    /**
     * 用户等级 賦值方法set
     * @param userLevel
     */
    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * 创建方式 0数据库添加 默认 1网页添加 取值方法get
     * @return createType
     */
    public int getCreateType() {
        return createType;
    }

    /**
     * 创建方式 0数据库添加 默认 1网页添加 賦值方法set
     * @param createType
     */
    public void setCreateType(int createType) {
        this.createType = createType;
    }

    /**
     * 更新方式 0数据库 1网页 取值方法get
     * @return updateType
     */
    public int getUpdateType() {
        return updateType;
    }

    /**
     * 更新方式 0数据库 1网页 賦值方法set
     * @param updateType
     */
    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}