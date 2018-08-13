package com.kokoharry.site.system.dao;

import com.kokoharry.site.system.bean.RoleMenuRelation;
import org.springframework.stereotype.Repository;

public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {
    int deleteByMenuCode(String menuCode);
}