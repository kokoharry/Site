package com.kokoharry.site.job.controller;

import com.kokoharry.site.job.bean.AttendanceJob;
import com.kokoharry.site.job.bean.Job;
import com.kokoharry.site.job.service.IJobService;
import com.kokoharry.site.system.bean.Role;
import com.kokoharry.site.system.bean.RoleMenuRelation;
import com.kokoharry.site.system.bean.User;
import com.kokoharry.site.system.controller.BaseController;
import com.kokoharry.site.util.RoleUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by luyb on 2017/9/6.
 */
@Controller
@Slf4j
@RequestMapping(value="/job")
public class JobController extends BaseController {

    @Autowired
    private IJobService jobService;

    /**
     * 页面跳转
     * @param modelAndView
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "index")
    public ModelAndView index(ModelAndView modelAndView, String menuCode) {
        User user = getCurrentUser();
        Role role = user.getRole();
        List<RoleMenuRelation> list = role.getRoleMenuRelations();
        for(RoleMenuRelation relation : list){
            if(menuCode!=null && menuCode.equals(relation.getMenuCode())){
                modelAndView.addObject("permission", RoleUtil.setPermissions(relation.getOperationAuthority()));
            }
        }
        modelAndView.setViewName("job/jobTable");
        modelAndView.addObject("menuCode",menuCode);
        return modelAndView;
    }

    /**
     * 用户列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sreach",method= RequestMethod.POST)
    public Map<String,Object> getJobs(int limit, int start, Job job) {
        Map<String,Object> map = new HashMap<>();
        List<Job> list = jobService.getJobForPage(start,limit,job);
        map.put("data",list);
        map.put("total",jobService.getJobCountForPage(job));
        return map;
    }

    /**
     * 添加打卡任務
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add",method= RequestMethod.GET)
    public int addJob(String userName,String password,String taskName,String cron) {
        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("password",password);
        map.put("taskName",taskName);
        int result = jobService.addQuartzJob(map,cron, AttendanceJob.class);
        return result;
    }


    /**
     * 关闭定时任务
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method= RequestMethod.GET)
    public int deleteJob(String taskName) {
        int result = jobService.closeQuartzJob(taskName);
        return result;
    }

    /**
     * 修改任务
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update",method= RequestMethod.GET)
    public int updateJob(String taskName,String cron) {
        int result = jobService.editQuartzCron(taskName,cron);
        return result;
    }

}