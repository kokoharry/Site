package com.kokoharry.site.system.bean;

import java.io.Serializable;

/**
 * Created by luyb on 2017/9/21.
 */
public class BaseDomain implements Serializable{

    /**
     * 主键自增
     */
    private long id;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private long createUser;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 更新人
     */
    private long updateUser;


    /**
     * 主键自增 取值方法get
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 主键自增 賦值方法set
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 创建时间 取值方法get
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间 賦值方法set
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人 取值方法get
     * @return createUser
     */
    public long getCreateUser() {
        return createUser;
    }

    /**
     * 创建人 賦值方法set
     * @param createUser
     */
    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    /**
     * 更新时间 取值方法get
     * @return updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间 賦值方法set
     * @param updateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人 取值方法get
     * @return updateUser
     */
    public long getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人 賦值方法set
     * @param updateUser
     */
    public void setUpdateUser(long updateUser) {
        this.updateUser = updateUser;
    }
}
