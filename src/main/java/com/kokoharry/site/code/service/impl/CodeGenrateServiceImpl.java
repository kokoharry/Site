package com.kokoharry.site.code.service.impl;

import com.kokoharry.site.code.bean.Property;
import com.kokoharry.site.code.dao.CodeGenrateMapper;
import com.kokoharry.site.code.dao.IJdbcDao;
import com.kokoharry.site.code.service.ICodeGenrateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.kokoharry.site.code.bean.Class;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luyb on 2017/9/6.
 */
@Service("codeGenrateService")
public class CodeGenrateServiceImpl implements ICodeGenrateService {
    /**
     * 日志类
     */
    private static Logger logger = LogManager.getLogger(CodeGenrateServiceImpl.class);

    @Resource
    private CodeGenrateMapper codeGenrateMapper;

    @Resource
    private IJdbcDao jdbcDaoImpl;

    @Override
    public String createTableBySqlUpload(String sql) {
        //找到创建的表名
        String tempTableName = "";
        Matcher matcher = Pattern.compile("CREATE TABLE [`]*([^\\s`]*)",Pattern.CASE_INSENSITIVE).matcher(sql);
        if(matcher.find()){
            String tableName = matcher.group(1);
            tempTableName = "tempTable_"+tableName+ "_"+UUID.randomUUID();
            sql = sql.replace(tableName,tempTableName);
            codeGenrateMapper.doSql(sql);
        }else{
            // 没有找到表明
            System.out.println("没有找到tablename");
        }
        return tempTableName;
    }

    /**
     * 根据表名构建class Bean以及属性propertys
     * @param tableName
     * @return
     */
    public List<Class> getTablesAndColumns(String tableName){
        try {
            List<Class> result = codeGenrateMapper.selectTables(tableName);
            for(Class classObj : result){
                List<Property> list = codeGenrateMapper.selectColumns(tableName);
                classObj.setProperties(list);
            }
            return result;
        } catch (Exception ex) {
            logger.error("根据表名构建Bean失败", ex);
        }
        return null;
    }

    /**
     * 根据表名构建class Bean以及属性propertys
     * @param tableName
     * @return
     */
    public List<Class> getTablesAndColumnsByJDBC(String jdbcUrl, String userName, String password,String tableName){
        List<Class> resultRe = new ArrayList<>();
        try {
            try {
                String dbName = (jdbcUrl.split("/")[jdbcUrl.split("/").length - 1]).split("\\?")[0];
                Class result = jdbcDaoImpl.getClassForGenrate(jdbcUrl,userName,password,dbName,tableName);
                List<Property> list = jdbcDaoImpl.selectColumns(jdbcUrl,userName,password,dbName,tableName);
                result.setProperties(list);
                resultRe.add(result);
            } catch (Exception ex) {
                logger.error("根据提供的数据库属性，获取可以连接的数据库表名", ex);
            }
            return resultRe;
        } catch (Exception ex) {
            logger.error("根据表名构建Bean失败", ex);
        }
        return null;
    }

    /**
     * 根据提供的数据库属性，获取可以连接的数据库表名
     * @return
     */
    public List<String> getTablesByJDBC(String jdbcUrl, String userName, String password){
        List<String> result = new ArrayList<>();
        try {
            String dbName = (jdbcUrl.split("/")[jdbcUrl.split("/").length - 1]).split("\\?")[0];
            result = jdbcDaoImpl.getTables(jdbcUrl,userName,password,dbName);
        } catch (Exception ex) {
            logger.error("根据提供的数据库属性，获取可以连接的数据库表名", ex);
        }
        return result;
    }

    @Override
    public int dropTempTable(String tableName) {
        return codeGenrateMapper.doDropTable(tableName);
    }

}
