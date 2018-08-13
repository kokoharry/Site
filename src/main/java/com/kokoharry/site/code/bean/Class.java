package com.kokoharry.site.code.bean;

import com.kokoharry.site.util.StringUtil;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * 封装类信息
 */
public class Class {

    private String packagename;

    private String classname;

    private String tableName;

    private String comment;

    private List<Property> properties;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassname() {
//        this.classname = classname.contains("Temptable")?
        return StringUtil.upperCase(StringUtil.camelCaseName(classname));
    }

    public String getClassnameNew() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
