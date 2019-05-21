package cn.stylefeng.guns.modular.plan.controller;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.modular.system.warpper.NewsWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Plan;
import cn.stylefeng.guns.modular.plan.service.IPlanService;

import java.util.List;
import java.util.Map;

/**
 * 解决方案管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 14:07:51
 */
@Controller
@RequestMapping("/plan")
public class PlanController extends BaseController {

    private String PREFIX = "/plan/plan/";

    @Autowired
    private IPlanService planService;

    /**
     * 跳转到解决方案管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "plan.html";
    }

    /**
     * 跳转到添加解决方案管理
     */
    @RequestMapping("/plan_add")
    public String planAdd() {
        return PREFIX + "plan_add.html";
    }

    /**
     * 跳转到修改解决方案管理
     */
    @RequestMapping("/plan_update/{planId}")
    public String planUpdate(@PathVariable Integer planId, Model model) {
        Plan plan = planService.selectById(planId);
        model.addAttribute("item",plan);
        LogObjectHolder.me().set(plan);
        return PREFIX + "plan_edit.html";
    }

    /**
     * 获取解决方案管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String title) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("title", title);
        return planService.selectList(entityWrapper);
    }

    /**
     * 新增解决方案管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Plan plan) {
        planService.insert(plan);
        return SUCCESS_TIP;
    }

    /**
     * 删除解决方案管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer planId) {
        planService.deleteById(planId);
        return SUCCESS_TIP;
    }

    /**
     * 修改解决方案管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Plan plan) {
        planService.updateById(plan);
        return SUCCESS_TIP;
    }

    /**
     * 解决方案管理详情
     */
    @RequestMapping(value = "/detail/{planId}")
    @ResponseBody
    public Object detail(@PathVariable("planId") Integer planId) {
        return planService.selectById(planId);
    }
}
