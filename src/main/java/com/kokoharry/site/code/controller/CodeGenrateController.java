package com.kokoharry.site.code.controller;

import com.kokoharry.site.code.service.ICodeGenrateService;
import com.kokoharry.site.system.bean.Role;
import com.kokoharry.site.system.bean.RoleMenuRelation;
import com.kokoharry.site.system.bean.User;
import com.kokoharry.site.system.controller.BaseController;
import com.kokoharry.site.util.CodeGenrateUtil;
import com.kokoharry.site.util.RoleUtil;
import com.kokoharry.site.util.ZipUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * Created by luyb on 2017/9/6.
 */
@Controller
@RequestMapping(value="/codeGenrate")
public class CodeGenrateController extends BaseController {
    /**
     * 日志类
     */
    private static Logger logger = LogManager.getLogger(CodeGenrateController.class);

    @Resource
    public ICodeGenrateService codeGenrateService;

    @RequiresAuthentication
    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView, String menuCode) {
        logger.debug("/codeGenrate/index action request param:{menucode:"+menuCode+"}");
        User user = getCurrentUser();
        Role role = user.getRole();
        List<RoleMenuRelation> list = role.getRoleMenuRelations();
        for(RoleMenuRelation relation : list){
            if(menuCode!=null && menuCode.equals(relation.getMenuCode())){
                modelAndView.addObject("permission", RoleUtil.setPermissions(relation.getOperationAuthority()));
            }
        }
        modelAndView.setViewName("codeGenrate/sql2CodeFile");
        modelAndView.addObject("menuCode",menuCode);
        return modelAndView;
    }

    @RequiresAuthentication
    @RequestMapping("uploadSql")
    public void uploadSql(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "inputSql",
            required=false) MultipartFile inputSql, @RequestParam("genrateScope") String genrateScope){
        logger.debug("/codeGenrate/uploadSql action request param：{myFile:" + inputSql+",genrateScope:"+genrateScope+"}");
        try {
            byte b[] = inputSql.getBytes();
            String sql = autoDecodeHandler(b);
            String realPath= request.getServletContext().getRealPath("/");
            logger.debug("/codeGenrate/uploadSql action freemarker templates path：" + realPath);
            String tableName = codeGenrateService.createTableBySqlUpload(sql);
            logger.debug("/codeGenrate/uploadSql action create temp table tableName：" + tableName);
            File file = new File(realPath+"tempFiles/"+ tableName+".zip");
            //创建完成了临时表
            try {
                List<File> list = CodeGenrateUtil.getInstance().process(genrateScope,codeGenrateService.getTablesAndColumns
                        (tableName),tableName);
                //6.创建zip文件
                ZipUtil.createFile(realPath+"tempFiles/", tableName+".zip");

                FileOutputStream outStream = new FileOutputStream(file);
                ZipOutputStream toClient = new ZipOutputStream(outStream);
                //7.将files打包成zip文件
                ZipUtil.zipFile(list, toClient);
                toClient.close();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int reslut  = codeGenrateService.dropTempTable(tableName);
            logger.debug("/codeGenrate/uploadSql action drop temp table tableName：" + tableName);
            //8.下载zip文件，并删除服务器源文件
            ZipUtil.downloadFile(file, response, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresAuthentication
    @RequestMapping("selectIndex")
    public ModelAndView selectIndex(ModelAndView modelAndView, String menuCode) {
        logger.debug("/codeGenrate/selectIndex action request param:{menucode:"+menuCode+"}");
        User user = getCurrentUser();
        Role role = user.getRole();
        List<RoleMenuRelation> list = role.getRoleMenuRelations();
        for(RoleMenuRelation relation : list){
            if(menuCode!=null && menuCode.equals(relation.getMenuCode())){
                modelAndView.addObject("permission", RoleUtil.setPermissions(relation.getOperationAuthority()));
            }
        }
        modelAndView.setViewName("codeGenrate/selectTable2CodeFile");
        modelAndView.addObject("menuCode",menuCode);
        return modelAndView;
    }

    @RequiresAuthentication
    @RequestMapping("selectTables")
    @ResponseBody
    public List<String> selectTables(String jdbcUrl, String userName, String password) {
        logger.debug("/codeGenrate/selectTables action request param:{jdbcUrl:"+jdbcUrl+",userName:"+ userName+"," +
                "password:"+password+"}");
        List<String> result = codeGenrateService.getTablesByJDBC(jdbcUrl,userName,password);
        return result;
    }

    @RequiresAuthentication
    @RequestMapping("table2Code")
    public void table2Code(HttpServletRequest request, HttpServletResponse response,
                           String genrateScope, String jdbcUrl, String userName, String password, String tableName) {
        logger.debug("/codeGenrate/table2Code action request param:{jdbcUrl:"+jdbcUrl+",userName:"+ userName+"," +
                "password:"+password+",tableName:"+tableName+",genrateScope:"+genrateScope+"}");
        try {
            String realPath= request.getServletContext().getRealPath("/");
            logger.debug("/codeGenrate/table2Code action freemarker templates path：" + realPath);
            File file = File.createTempFile( tableName,".zip");
            //创建完成了临时表
            try {
                List<File> list = CodeGenrateUtil.getInstance().process(genrateScope,
                        codeGenrateService.getTablesAndColumnsByJDBC(jdbcUrl,userName,password,tableName),
                        tableName);
                //创建zip文件
//                ZipUtil.createFile(realPath+"tempFiles/", tableName+".zip");

                FileOutputStream outStream = new FileOutputStream(file);
                ZipOutputStream toClient = new ZipOutputStream(outStream);
                //7.将files打包成zip文件
                ZipUtil.zipFile(list, toClient);
                toClient.close();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //8.下载zip文件，并删除服务器源文件
            ZipUtil.downloadFile(file, response, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}