package com.kokoharry.site.system.service;

import com.kokoharry.site.system.bean.User;

import java.util.List;

/**
 * Created by luyb on 2017/9/6.
 */
public interface IUserService {

    boolean checkUserName(String userName);

    User getUserForLogin(String userName, String password);

    /**
     *
     * @param fristNum
     * @param limitNum
     * @return
     */
    List<User> getUsersForPage(int fristNum, int limitNum);

    /**
     *计算数量
     * @return
     */
    int getUsersCount();

    /**
     * 添加
     * @param user
     * @return
     */
    User addUser(User user);


    /**
     * 删除
     * @param id
     * @return
     */
    int deleteUser(long id);

    /**
     * 编辑
     * @param user
     * @return
     */
    int editUser(User user);
}
