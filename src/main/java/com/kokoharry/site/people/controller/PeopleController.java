package com.kokoharry.site.people.controller;

import com.kokoharry.site.people.bean.People;
import com.kokoharry.site.people.service.IPeopleService;
import com.kokoharry.site.system.bean.Menu;
import com.kokoharry.site.system.bean.Role;
import com.kokoharry.site.system.bean.RoleMenuRelation;
import com.kokoharry.site.system.bean.User;
import com.kokoharry.site.system.controller.BaseController;
import com.kokoharry.site.system.service.IMenuService;
import com.kokoharry.site.system.service.IRoleService;
import com.kokoharry.site.system.service.IUserService;
import com.kokoharry.site.util.RoleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="/people")
public class PeopleController extends BaseController {
    /**
     * 日志类
     */
    public static Logger logger = LogManager.getLogger(PeopleController.class);

    @Autowired
    private IPeopleService peopleService;

    /**
     * 页面跳转
     * @param modelAndView
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "index")
    public ModelAndView index(ModelAndView modelAndView, String menuCode) {
        User user = getCurrentUser();
        Role role = user.getRole();
        List<RoleMenuRelation> list = role.getRoleMenuRelations();
        for(RoleMenuRelation relation : list){
            if(menuCode!=null && menuCode.equals(relation.getMenuCode())){
                modelAndView.addObject("permission", RoleUtil.setPermissions(relation.getOperationAuthority()));
            }
        }
        modelAndView.setViewName("people/peopleTable");
        modelAndView.addObject("menuCode",menuCode);
        return modelAndView;
    }

    /**
     * 用户列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sreach",method= RequestMethod.POST)
    public Map<String,Object> getUsers(int limit, int start, People people) {
        Map<String,Object> map = new HashMap<>();
        List<People> list = peopleService.getPeopleForPage(start,limit,people);
        map.put("data",list);
        map.put("total",peopleService.getPeopleCountForPage(people));
        return map;
    }

}