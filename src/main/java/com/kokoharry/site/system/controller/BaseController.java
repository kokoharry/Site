package com.kokoharry.site.system.controller;

import com.kokoharry.site.config.ShiroDbRealm;
import com.kokoharry.site.system.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

/**
 * Created by luyb on 2017/9/27.
 */
public class BaseController {

    /**
     * 日志类
     */
    private static Logger logger = LogManager.getLogger(BaseController.class);

    @ExceptionHandler({AuthorizationException.class})
    public ModelAndView processAuthorizationException(AuthorizationException ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("ErrorMsg", ex.getMessage());
        mv.addObject("ErrorTitle", "用户权限不足");
        // 为了区分，跳转掉另一个视图
        mv.setViewName("error/error");
        return mv;
    }

    public User getCurrentUser(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        if(user == null){
            throw new AuthorizationException("用户还没有登录");
        }
        return user;
    }

    /**
     * 收集自动转码处理类
     * @param message
     */
    public String autoDecodeHandler(byte[] message) {
        String messageStr = "";
        try {
            messageStr = new String(message,encodingHandleData(message));
        } catch (UnsupportedEncodingException e) {
            logger.error("自动转换编码异常",e);
        }
        return messageStr;
    }

    /**
     * 根据字节信息获取当前字节信息的编码
     * @param bytes 字节信息
     * @return
     */
    public static String encodingHandleData(byte[] bytes) {
        //设置默认编码，通过获取系统编码
        String DEFAULT_ENCODING = System.getProperty("file.encoding");
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        return encoding;
    }

}
