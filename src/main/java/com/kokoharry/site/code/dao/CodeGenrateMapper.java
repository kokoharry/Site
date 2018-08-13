package com.kokoharry.site.code.dao;

import com.kokoharry.site.code.bean.Property;
import com.kokoharry.site.system.dao.BaseMapper;
import com.kokoharry.site.code.bean.Class;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("codeGenrateMapper")
public interface CodeGenrateMapper extends BaseMapper<Object> {

    int doSql(String value);

    int doDropTable(String value);

    List<Property> selectColumns(String tableName);

    List<Class> selectTables(String tableName);

}