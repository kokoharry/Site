package com.kokoharry.site.system.dao;

import com.kokoharry.site.system.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    int selectExits(User user);

    User selectUserForLogin(@Param("userName") String userName, @Param("password") String password);
}