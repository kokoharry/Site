package com.kokoharry.site.config;


import com.kokoharry.site.system.bean.Role;
import com.kokoharry.site.system.bean.RoleMenuRelation;
import com.kokoharry.site.system.bean.User;
import com.kokoharry.site.system.service.IRoleService;
import com.kokoharry.site.system.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyb on 2017/9/26.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    public static final String SESSION_USER_KEY = "kokoharry";

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(user.getRoleCode().trim());
        Role role = roleService.getPermissionsByRoleCode(user.getRoleCode().trim());
        List<String> list = new ArrayList<>();
        for(RoleMenuRelation roleMenuRelation : role.getRoleMenuRelations()){
            list.add(roleMenuRelation.getMenuCode()+";"+roleMenuRelation.getOperationAuthority());
        }
        info.addStringPermissions(list);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 把token转换成User对象
        User userLogin = tokenToUser((UsernamePasswordToken) authenticationToken);
        // 验证用户是否可以登录
        User user = userService.getUserForLogin(userLogin.getUserName(),userLogin.getPassword());
        if(user == null){
            return null; // 异常处理，找不到数据
        }
        // 设置session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(ShiroDbRealm.SESSION_USER_KEY, user);
        //当前 Realm 的 name
        String realmName = this.getName();
        //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
        Object principal = authenticationToken.getPrincipal();
        return new SimpleAuthenticationInfo(principal, userLogin.getPassword(), realmName);
    }

    private User tokenToUser(UsernamePasswordToken authcToken) {
        User user = new User();
        user.setUserName(authcToken.getUsername());
        user.setPassword(String.valueOf(authcToken.getPassword()));
        return user;
    }

}
