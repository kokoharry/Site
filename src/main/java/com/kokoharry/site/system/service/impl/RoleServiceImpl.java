package com.kokoharry.site.system.service.impl;

import com.kokoharry.site.system.bean.Role;
import com.kokoharry.site.system.dao.RoleMapper;
import com.kokoharry.site.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luyb on 2017/9/6.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleById(long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Role getRoleByCode(String code) {
        return roleMapper.selectOneByCode(code);
    }

    @Override
    public Role getPermissionsByRoleCode(String roleCode) {
        return roleMapper.getRolePermissions(roleCode);
    }

    @Override
    public List<Role> getRolesForPage(int fristNum, int limitNum) {
        return roleMapper.getListForPage(null,fristNum,limitNum);
    }

    @Override
    public int getRolesCount() {
        return roleMapper.selectTotalCount();
    }

    @Override
    public Role addRole(Role role) {
            //通过网络模式添加
        role.setCreateType(1);
        roleMapper.insert(role);
        return role;
    }

    @Override
    public int deleteRole(long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editRole(Role role) {
        role.setUpdateType(1);
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public List<Role> getRolesByMenuCodeForRelation(String menuCode) {
        return roleMapper.getRolesByMenuCodeForRelation(menuCode);
    }

    @Override
    public List<Role> getOtherRolesByMenuCodeForRelation(String menuCode) {
        List<Role> list = roleMapper.getOtherRolesByMenuCodeForRelation(menuCode);
        return list;
    }


}
