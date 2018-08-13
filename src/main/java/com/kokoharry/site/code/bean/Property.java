package com.kokoharry.site.code.bean;

import com.kokoharry.site.util.StringUtil;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luyb on 2017/8/9.
 */
public class Property {
    private String propertyName;
    private String columnName;

    private String propertyScope = "private";
    private String type;
    private String comment;
    private static Map<String,String> map = new HashMap<>();

    public Property(String propertyScope){
        this.propertyScope = propertyScope;
    }

    public Property(){
        map.put("tinyint","int");
        map.put("int","int");
        map.put("bigint","long");
        map.put("integer","int");
        map.put("varchar","String");
        map.put("datetime","Date");
        map.put("timestamp","Date");
    }

    public String getPropertyName() {
        return StringUtil.camelCaseName(propertyName);
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getType() {
        return getTypeFromMap(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPropertyScope() {
        return propertyScope;
    }

    public void setPropertyScope(String propertyScope) {
        this.propertyScope = propertyScope;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    private String getTypeFromMap(String type){
        return map.get(type);
    }
}
