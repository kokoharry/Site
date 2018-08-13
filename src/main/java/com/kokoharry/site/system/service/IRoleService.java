package com.kokoharry.site.system.service;

import com.kokoharry.site.system.bean.Role;

import java.util.List;

/**
 * Created by luyb on 2017/9/6.
 */
public interface IRoleService {

    Role getRoleById(long id);

    Role getRoleByCode(String code);

    Role getPermissionsByRoleCode(String roleCode);

    List<Role> getRolesForPage(int fristNum, int limitNum);

    int getRolesCount();

    Role addRole(Role role);

    int deleteRole(long id);

    int editRole(Role role);

    List<Role> getRolesByMenuCodeForRelation(String menuCode);

    List<Role> getOtherRolesByMenuCodeForRelation(String menuCode);

}
