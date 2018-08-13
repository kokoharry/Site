package com.kokoharry.site.system.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.kokoharry.site.config.ShiroDbRealm;
import com.kokoharry.site.system.bean.Menu;
import com.kokoharry.site.system.bean.Role;
import com.kokoharry.site.system.bean.User;
import com.kokoharry.site.system.service.IMenuService;
import com.kokoharry.site.system.service.IRoleService;
import com.kokoharry.site.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by luyb on 2017/9/6.
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {
    /**
     * 日志类
     */
    public static Logger logger = LogManager.getLogger(LoginController.class);

    @Resource
    private IUserService userService;

    @Resource
    public IRoleService roleService;

    @Resource
    public IMenuService menuService;

    @Autowired
    private Producer captchaProducer;

    /**
     * 登录判断 成功跳转后台，失败返回登录页面
     * @param userName
     * @param password
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "login",method= RequestMethod.POST)
    public ModelAndView login(String userName, String password, String kaptcha, ModelAndView modelAndView, HttpServletRequest request) {
        logger.debug("/login/login request Pram =  {userName:" + userName + ";password:" + password
                +";kaptcha:"+kaptcha+"}");
        boolean flag = false;
        String original =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotEmpty(kaptcha)) {
            if (kaptcha.equalsIgnoreCase(original)) {
                flag = true;
            }
        }
        if(flag){
            String result = shiroLogin(userName, password);
            if("SUCC".equals(result)){
                User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
                if(user != null){
                    //登录成功
                    Role role = roleService.getPermissionsByRoleCode(user.getRoleCode());
                    List<Menu> list = menuService.getMenusByRoleCode(user.getRoleCode());
                    user.setRole(role);
                    modelAndView.setViewName("frame/index");
                    modelAndView.addObject("user",user);
                    modelAndView.addObject("role",role);
                    modelAndView.addObject("menus",list);
                }
            }else{
                //登录失败返回登录页面提示错误信息
                modelAndView.setViewName("login/login");
                modelAndView.addObject("resultMsg",result);
            }
        }else{
            //验证码失败
            modelAndView.setViewName("login/login");
            modelAndView.addObject("resultMsg","验证码输入错误");
        }

        return modelAndView;
    }

    /**
     * login 页面跳转
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "loginPage")
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("login/login");
        modelAndView.addObject("timestamp", System.currentTimeMillis());
        return modelAndView;
    }

    /**
     * login 主页面跳转
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "mainPage")
    public ModelAndView mainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("frame/main");
        return modelAndView;
    }

    /**
     * 验证用户名是否存在
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkUserName",method= RequestMethod.POST)
    public String checkUserName(String userName) {
        logger.debug("/login/checkUserName request Pram =  {userName:"+userName+"}");
        Boolean flag =  userService.checkUserName(userName);
        logger.debug("/login/checkUserName response result =  {"+flag.toString()+"}");
        return flag.toString();
    }

    private String shiroLogin(String userName,String password) {
        // 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray(), null);
        token.setRememberMe(true);
        // shiro登陆验证
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return "用户不存在或者密码错误！";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或者密码错误！";
        } catch (AuthenticationException ex) {
            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "SUCC";
    }

    /**
     * 生成带验证码的图片
     * @param modelAndView
     * @param request
     * @param response
     * @param timestamp
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getCaptchaImage.jpg", method = RequestMethod.GET)
    public ModelAndView getCaptchaImage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(value = "timestamp", required = false) String timestamp) throws IOException {
        // 处理时间戳
        if (StringUtils.isEmpty(timestamp)) {
            modelAndView.addObject("timestamp", System.currentTimeMillis());
        } else {
            modelAndView.addObject("timestamp", timestamp);
        }
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        logger.debug("生成了一个验证码，内容为：" + capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView) throws IOException{
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try{
                subject.logout();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        modelAndView.setViewName("/login/login");
        return modelAndView;
    }
}