package com.kokoharry.site.code.dao;


import com.kokoharry.site.code.bean.Class;
import com.kokoharry.site.code.bean.Property;

import java.util.List;

/**
 * Created by luyb on 2017/10/31.
 */
public interface IJdbcDao {

    List<String> getTables(String jdbcUrl, String userName, String password,String dbName);

    Class getClassForGenrate(String jdbcUrl, String userName, String password, String dbName, String
            tableName);

    List<Property> selectColumns(String jdbcUrl, String userName, String password, String dbName, String tableName);
}
