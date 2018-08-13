package com.kokoharry.site.system.controller;

import com.kokoharry.site.system.bean.Menu;
import com.kokoharry.site.system.bean.Role;
import com.kokoharry.site.system.bean.RoleMenuRelation;
import com.kokoharry.site.system.bean.User;
import com.kokoharry.site.system.service.IMenuService;
import com.kokoharry.site.system.service.IRoleService;
import com.kokoharry.site.system.service.IUserService;
import com.kokoharry.site.util.RoleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by luyb on 2017/9/6.
 */
@Controller
@RequestMapping(value="/system")
public class SystemController extends BaseController {
    /**
     * 日志类
     */
    public static Logger logger = LogManager.getLogger(SystemController.class);

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuService menuService;

    /**
     * 用户列表页面跳转
     * @param modelAndView
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "index")
    public ModelAndView index(ModelAndView modelAndView, String menuCode) {
        logger.debug("/system/index action request param:{menucode:"+menuCode+"}");
        User user = getCurrentUser();
        Role role = user.getRole();
        List<RoleMenuRelation> list = role.getRoleMenuRelations();
        for(RoleMenuRelation relation : list){
            if(menuCode!=null && menuCode.equals(relation.getMenuCode())){
                modelAndView.addObject("permission", RoleUtil.setPermissions(relation.getOperationAuthority()));
            }
        }
        modelAndView.setViewName("manager/usersTable");
        modelAndView.addObject("menuCode",menuCode);
        return modelAndView;
    }

    /**
     * 用户列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "users",method= RequestMethod.POST)
    public Map<String,Object> getUsers(int limit, int start) {
        logger.debug("/system/users action request param:{"+ limit+ ";"+start+"}");
        Map<String,Object> map = new HashMap<>();
        List<User> list = userService.getUsersForPage(start,limit);
        map.put("data",list);
        map.put("total",userService.getUsersCount());
        logger.debug("/system/users response result =  {" + map + "}");
        return map;
    }

    /**
     * 用户添加
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userAdd",method= RequestMethod.POST)
    public long addUser(User user) {
        logger.debug("/system/userAdd action request param:{"+ user+"}");
        User userCurrent = getCurrentUser();
        user.setCreateUser(userCurrent.getId());
        user = userService.addUser(user);
        logger.debug("/system/userAdd action response result:{"+ user+"}");
        if(user != null && user.getId() > 0){
            return user.getId();
        }
        return -1;
    }

    /**
     * 用户删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userDel",method= RequestMethod.POST)
    public long deleteUser(long id) {
        logger.debug("/system/userDel action request param:{"+ id+"}");
        int result = userService.deleteUser(id);
        logger.debug("/system/userDel action response result:{"+ result+"}");
        return result;
    }

    /**
     * 用户编辑
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userEdit",method= RequestMethod.POST)
    public int editUser(User user) {
        logger.debug("/system/userEdit action request param:{"+ user+"}");
        User userCurrent = getCurrentUser();
        user.setUpdateUser(userCurrent.getId());
        int result = userService.editUser(user);
        logger.debug("/system/userEdit action response result:{"+ result+"}");
        return result;
    }

    /**
     * 角色列表页面跳转
     * @param modelAndView
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "roleIndex")
    public ModelAndView RoleIndex(ModelAndView modelAndView, String menuCode) {
        logger.debug("/system/roleIndex action request param:{menucode:"+menuCode+"}");
        User user = getCurrentUser();
        Role role = user.getRole();
        List<RoleMenuRelation> list = role.getRoleMenuRelations();
        for(RoleMenuRelation relation : list){
            if(menuCode!=null && menuCode.equals(relation.getMenuCode())){
                modelAndView.addObject("permission",RoleUtil.setPermissions(relation.getOperationAuthority()));
            }
        }
        modelAndView.setViewName("manager/rolesTable");
        modelAndView.addObject("menuCode",menuCode);
        return modelAndView;
    }

    /**
     * 角色列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "roles",method= RequestMethod.POST)
    public Map<String,Object> getRoles(int limit, int start) {
        logger.debug("/system/roles action request param:{"+ limit+ ";"+start+"}");
        Map<String,Object> map = new HashMap<>();
        List<Role> list = roleService.getRolesForPage(start,limit);
        map.put("data",list);
        map.put("total",roleService.getRolesCount());
        logger.debug("/system/roles response result =  {" + map + "}");
        return map;
    }

    /**
     * 角色添加
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "roleAdd",method= RequestMethod.POST)
    public long addRole(Role role) {
        logger.debug("/system/roleAdd action request param:{"+ role+"}");
        User userCurrent = getCurrentUser();
        role.setCreateUser(userCurrent.getId());
        role = roleService.addRole(role);
        logger.debug("/system/roleAdd action response result:{"+ role+"}");
        if(role != null && role.getId() > 0){
            return role.getId();
        }
        return -1;
    }

    /**
     * 角色删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "roleDel",method= RequestMethod.POST)
    public long deleteRole(long id) {
        logger.debug("/system/roleDel action request param:{"+ id+"}");
        int result = roleService.deleteRole(id);
        logger.debug("/system/roleDel action response result:{"+ result+"}");
        return result;
    }

    /**
     * 角色编辑
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "roleEdit",method= RequestMethod.POST)
    public int editRole(Role role) {
        logger.debug("/system/roleEdit action request param:{"+ role+"}");
        User userCurrent = getCurrentUser();
        role.setUpdateUser(userCurrent.getId());
        int result = roleService.editRole(role);
        logger.debug("/system/roleEdit action response result:{"+ result+"}");
        return result;
    }

    /**
     * 角色列表页面跳转
     * @param modelAndView
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "menuIndex")
    public ModelAndView menuIndex(ModelAndView modelAndView, String menuCode) {
        logger.debug("/system/menuIndex action request param:{menucode:"+menuCode+"}");
        User user = getCurrentUser();
        Role role = user.getRole();
        List<RoleMenuRelation> list = role.getRoleMenuRelations();
        for(RoleMenuRelation relation : list){
            if(menuCode!=null && menuCode.equals(relation.getMenuCode())){
                modelAndView.addObject("permission",RoleUtil.setPermissions(relation.getOperationAuthority()));
            }
        }
        modelAndView.setViewName("manager/menusTable");
        modelAndView.addObject("menuCode",menuCode);
        return modelAndView;
    }

    /**
     * 角色列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "menus",method= RequestMethod.POST)
    public Map<String,Object> getMenus(int limit, int start) {
        logger.debug("/system/menus action request param:{"+ limit+ ";"+start+"}");
        Map<String,Object> map = new HashMap<>();
        List<Menu> list = menuService.getMenusForPage(start,limit);
        map.put("data",list);
        map.put("total",menuService.getMenusCount());
        logger.debug("/system/menus response result =  {" + map + "}");
        return map;
    }

    /**
     * 获取父菜单选项tree列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getParentMenus",method= RequestMethod.POST)
    public List<Map<String,Object>>  getParentMenus() {
        logger.debug("/system/getParentMenus action request");
        User user = getCurrentUser();
        List<Map<String,Object>>  list = menuService.getParentMenusForTree(user.getRoleCode());
        logger.debug("/system/getParentMenus response result =  {" + list + "}");
        return list;
    }


    /**
     * 菜单添加
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "menuAdd",method= RequestMethod.POST)
    public long addMenu(Menu menu) {
        logger.debug("/system/menuAdd action request param:{"+ menu+"}");
        User userCurrent = getCurrentUser();
        menu.setCreateUser(userCurrent.getId());
        menu = menuService.addMenu(menu);
        logger.debug("/system/menuAdd action response result:{"+ menu+"}");
        if(menu != null && menu.getId() > 0){
            return menu.getId();
        }
        return -1;
    }

    /**
     * 角色删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "menuDel",method= RequestMethod.POST)
    public long deleteMenu(long id) {
        logger.debug("/system/menuDel action request param:{"+ id+"}");
        int result = menuService.deleteMenu(id);
        logger.debug("/system/menuDel action response result:{"+ result+"}");
        return result;
    }

    /**
     * 根据菜单code或者菜单权限角色，以及所有角色
     * @param menuCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMenuRoles",method= RequestMethod.POST)
    public Map<String,Object> getMenuRoles(String menuCode) {
        logger.debug("/system/getMenuRoles action request param:{"+ menuCode+"}");
        Map<String,Object> result = new HashMap<>();
        List<Role> list = roleService.getRolesByMenuCodeForRelation(menuCode);
        result.put("have",list);
        List<Role> list1 = roleService.getOtherRolesByMenuCodeForRelation(menuCode);
        result.put("other",list1);
        logger.debug("/system/getMenuRoles action response result:{"+ result+"}");
        return result;
    }

    /**
     * 根据菜单code或者菜单权限角色，以及所有角色
     * @param menuCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "grantMenuRoles",method= RequestMethod.POST)
    public int grantMenuRoles(String menuCode, String roleCodes) {
        logger.debug("/system/grantMenuRoles action request param:{menuCode:"+ menuCode+",roleCodes:"+roleCodes+"}");
        User userCurrent = getCurrentUser();
        int result = menuService.grantMenuRoles(menuCode,roleCodes,userCurrent.getId());
        logger.debug("/system/grantMenuRoles action response result:{"+ result+"}");
        return result;
    }


}