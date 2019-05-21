package cn.stylefeng.guns.modular.companyInformation.controller;

import cn.stylefeng.guns.modular.system.warpper.CompanyNameWarpper;
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
import cn.stylefeng.guns.modular.system.model.Companyinformation;
import cn.stylefeng.guns.modular.companyInformation.service.ICompanyinformationService;

import java.util.List;
import java.util.Map;

/**
 * 公司信息管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 14:34:47
 */
@Controller
@RequestMapping("/companyinformation")
public class CompanyinformationController extends BaseController {

    private String PREFIX = "/companyInformation/companyinformation/";

    @Autowired
    private ICompanyinformationService companyinformationService;

    /**
     * 跳转到公司信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "companyinformation.html";
    }

    /**
     * 跳转到添加公司信息管理
     */
    @RequestMapping("/companyinformation_add")
    public String companyinformationAdd() {
        return PREFIX + "companyinformation_add.html";
    }

    /**
     * 跳转到修改公司信息管理
     */
    @RequestMapping("/companyinformation_update/{companyinformationId}")
    public String companyinformationUpdate(@PathVariable Integer companyinformationId, Model model) {
        Companyinformation companyinformation = companyinformationService.selectById(companyinformationId);
        model.addAttribute("item",companyinformation);
        model.addAttribute("companyName",companyinformation.getcompanyName());
        LogObjectHolder.me().set(companyinformation);
        return PREFIX + "companyinformation_edit.html";
    }

    /**
     * 获取公司信息管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String companyName) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("company_name",companyName);
        List<Map<String, Object>> list = companyinformationService.selectMaps(entityWrapper);
        return new CompanyNameWarpper(list).wrap();
    }

    /**
     * 新增公司信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Companyinformation companyinformation) {
        companyinformationService.insert(companyinformation);
        return SUCCESS_TIP;
    }

    /**
     * 删除公司信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer companyinformationId) {
        companyinformationService.deleteById(companyinformationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改公司信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Companyinformation companyinformation) {
        companyinformationService.updateById(companyinformation);
        return SUCCESS_TIP;
    }

    /**
     * 公司信息管理详情
     */
    @RequestMapping(value = "/detail/{companyinformationId}")
    @ResponseBody
    public Object detail(@PathVariable("companyinformationId") Integer companyinformationId) {
        return companyinformationService.selectById(companyinformationId);
    }
}
