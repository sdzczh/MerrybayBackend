package cn.stylefeng.guns.modular.consultingService.controller;

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
import cn.stylefeng.guns.modular.system.model.Consultingservice;
import cn.stylefeng.guns.modular.consultingService.service.IConsultingserviceService;

/**
 * 咨询服务管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 14:25:12
 */
@Controller
@RequestMapping("/consultingservice")
public class ConsultingserviceController extends BaseController {

    private String PREFIX = "/consultingService/consultingservice/";

    @Autowired
    private IConsultingserviceService consultingserviceService;

    /**
     * 跳转到咨询服务管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "consultingservice.html";
    }

    /**
     * 跳转到添加咨询服务管理
     */
    @RequestMapping("/consultingservice_add")
    public String consultingserviceAdd() {
        return PREFIX + "consultingservice_add.html";
    }

    /**
     * 跳转到修改咨询服务管理
     */
    @RequestMapping("/consultingservice_update/{consultingserviceId}")
    public String consultingserviceUpdate(@PathVariable Integer consultingserviceId, Model model) {
        Consultingservice consultingservice = consultingserviceService.selectById(consultingserviceId);
        model.addAttribute("item",consultingservice);
        LogObjectHolder.me().set(consultingservice);
        return PREFIX + "consultingservice_edit.html";
    }

    /**
     * 获取咨询服务管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String companyName) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("company_name", companyName);
        return consultingserviceService.selectList(entityWrapper);
    }

    /**
     * 新增咨询服务管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Consultingservice consultingservice) {
        consultingserviceService.insert(consultingservice);
        return SUCCESS_TIP;
    }

    /**
     * 删除咨询服务管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer consultingserviceId) {
        consultingserviceService.deleteById(consultingserviceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改咨询服务管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Consultingservice consultingservice) {
        consultingserviceService.updateById(consultingservice);
        return SUCCESS_TIP;
    }

    /**
     * 咨询服务管理详情
     */
    @RequestMapping(value = "/detail/{consultingserviceId}")
    @ResponseBody
    public Object detail(@PathVariable("consultingserviceId") Integer consultingserviceId) {
        return consultingserviceService.selectById(consultingserviceId);
    }
}
