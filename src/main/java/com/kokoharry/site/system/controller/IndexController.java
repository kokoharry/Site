package com.kokoharry.site.system.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by luyb on 2017/9/6.
 */
@Controller
public class IndexController {
    /**
     * 日志类
     */
    private static Logger logger = LogManager.getLogger(IndexController.class);

    @RequestMapping({ "/", "/index" })
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("test","lalalala");
        return modelAndView;
    }
}