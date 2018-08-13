package com.kokoharry.site.system.service.impl;

import com.kokoharry.site.system.bean.Menu;
import com.kokoharry.site.system.bean.RoleMenuRelation;
import com.kokoharry.site.system.dao.MenuMapper;
import com.kokoharry.site.system.dao.RoleMenuRelationMapper;
import com.kokoharry.site.system.service.IMenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luyb on 2017/9/6.
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {

    /**
     * 日志类
     */
    public static Logger logger = LogManager.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;

    @Override
    public List<Menu> getMenusByRoleCode(String roleCode) {

        List<Menu> result = new ArrayList<>();
        List<Menu> list = menuMapper.getMenusByRoleCode(roleCode);
        for(Menu menu : list){
            if(menu.getMenuParentCode()==null||"".equals(menu.getMenuParentCode())){
                //没有父节点code为主节点
                result.add(menu);
            }
            menu.setMenus(getMenusFromListByParentCode(list,menu.getMenuCode()));
        }
        return result;
    }

    @Override
    public List<Menu> getMenusForPage(int fristNum, int limitNum) {
        return menuMapper.getListForPage(null,fristNum,limitNum);
    }

    @Override
    public int getMenusCount() {
        return menuMapper.selectTotalCount();
    }

    @Override
    public Menu addMenu(Menu menu) {
        //通过网络模式添加
        menu.setCreateType(1);
        menuMapper.insert(menu);
        return menu;
    }

    @Override
    public int deleteMenu(long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editMenu(Menu menu) {
        return 0;
    }

    @Override
    public List<Map<String,Object>> getParentMenusForTree(String roleCode) {
        List<Menu> list =  getMenusByRoleCode(roleCode);
        List<Map<String,Object>> result = putNodesMenus(list);
        return result;
    }

    @Override
    public int grantMenuRoles(String menuCode, String roleCodes,long userCode) {
        try{
            String[] array = roleCodes.split(",");
            if(array != null && array.length>0){
                roleMenuRelationMapper.deleteByMenuCode(menuCode);
            }
            for(String string : array){
                RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
                roleMenuRelation.setMenuCode(menuCode);
                roleMenuRelation.setRoleCode(string);
                roleMenuRelation.setUpdateUser(userCode);
                roleMenuRelation.setCreateUser(userCode);
                roleMenuRelation.setOperationAuthority(0);
                roleMenuRelation.setUpdateType(1);
                roleMenuRelationMapper.insert(roleMenuRelation);
            }
            return 1;
        }catch (Exception e){
            logger.error("菜单角色授权异常",e);
        }
        return 0;

    }

    private List<Map<String,Object>> putNodesMenus(List<Menu> menus) {
        if(menus != null && menus.size() > 0){
            List<Map<String,Object>> result = new ArrayList<>();
            //存在菜单
            for(Menu menu : menus){
                Map<String,Object> map = new HashMap<>();
                map.put("text",menu.getMenuName());
                map.put("href",menu.getMenuCode());
                List<String> list = new ArrayList<>();
                list.add("4");
                map.put("tags",list);
                if(menu.getMenus()!=null && menu.getMenus().size()>0){
                    map.put("nodes",putNodesMenus(menu.getMenus()));
                }
                result.add(map);
            }
            return result;
        }
        return null;
    }

    private List<Menu> getMenusFromListByParentCode(List<Menu> list, String code){
        List<Menu> result = new ArrayList<>();
        for(Menu menu : list){
            if(menu.getMenuParentCode()!=null && menu.getMenuParentCode().equals(code)){
                result.add(menu);
            }
        }
        return result;
    }
}
