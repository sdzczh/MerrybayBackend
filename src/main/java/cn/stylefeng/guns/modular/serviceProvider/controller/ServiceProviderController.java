package cn.stylefeng.guns.modular.serviceProvider.controller;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.modular.system.warpper.ProviderWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.ServiceProvider;
import cn.stylefeng.guns.modular.serviceProvider.service.IServiceProviderService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

/**
 * 服务提供管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 11:35:50
 */
@Controller
@RequestMapping("/serviceProvider")
public class ServiceProviderController extends BaseController {

    private String PREFIX = "/serviceProvider/serviceProvider/";

    @Autowired
    private IServiceProviderService serviceProviderService;

    /**
     * 跳转到服务提供管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "serviceProvider.html";
    }

    /**
     * 跳转到添加服务提供管理
     */
    @RequestMapping("/serviceProvider_add")
    public String serviceProviderAdd() {
        return PREFIX + "serviceProvider_add.html";
    }

    /**
     * 跳转到修改服务提供管理
     */
    @RequestMapping("/serviceProvider_update/{serviceProviderId}")
    public String serviceProviderUpdate(@PathVariable Integer serviceProviderId, Model model) {
        ServiceProvider serviceProvider = serviceProviderService.selectById(serviceProviderId);
        model.addAttribute("item",serviceProvider);
        LogObjectHolder.me().set(serviceProvider);
        return PREFIX + "serviceProvider_edit.html";
    }

    /**
     * 获取服务提供管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String type) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if(!StrUtil.isEmpty(type)) {
            entityWrapper.eq("type", type);
        }
        List<Map<String, Object>> list = serviceProviderService.selectMaps(entityWrapper);
        return new ProviderWarpper(list).wrap();
    }

    /**
     * 新增服务提供管理
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestParam("img_url") MultipartFile img_url, ServiceProvider serviceProvider) {
        if (img_url.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = img_url.getOriginalFilename();
        String filePath = "C:/Users/12778/Desktop/img/";
        File dest = new File(filePath + fileName);
        try {
            img_url.transferTo(dest);
            serviceProvider.setImgLink(filePath + fileName);
            serviceProviderService.insert(serviceProvider);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除服务提供管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer serviceProviderId) {
        serviceProviderService.deleteById(serviceProviderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改服务提供管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ServiceProvider serviceProvider) {
        serviceProviderService.updateById(serviceProvider);
        return SUCCESS_TIP;
    }

    /**
     * 服务提供管理详情
     */
    @RequestMapping(value = "/detail/{serviceProviderId}")
    @ResponseBody
    public Object detail(@PathVariable("serviceProviderId") Integer serviceProviderId) {
        return serviceProviderService.selectById(serviceProviderId);
    }
}
