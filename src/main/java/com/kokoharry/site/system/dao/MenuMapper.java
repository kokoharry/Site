package com.kokoharry.site.system.dao;

import com.kokoharry.site.system.bean.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByRoleCode(String roleCode);

}