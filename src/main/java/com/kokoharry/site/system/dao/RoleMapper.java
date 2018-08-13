package com.kokoharry.site.system.dao;

import com.kokoharry.site.system.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    Role getRolePermissions(@Param("roleCode") String roleCode);

    List<Role> getRolesByMenuCodeForRelation(String menuCode);

    List<Role> getOtherRolesByMenuCodeForRelation(String menuCode);

}