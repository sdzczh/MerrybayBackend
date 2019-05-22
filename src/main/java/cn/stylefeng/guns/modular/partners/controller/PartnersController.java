package cn.stylefeng.guns.modular.partners.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Partners;
import cn.stylefeng.guns.modular.partners.service.IPartnersService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 合作伙伴管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 14:29:59
 */
@Controller
@RequestMapping("/partners")
public class PartnersController extends BaseController {

    private String PREFIX = "/partners/partners/";

    @Autowired
    private IPartnersService partnersService;

    /**
     * 跳转到合作伙伴管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "partners.html";
    }

    /**
     * 跳转到添加合作伙伴管理
     */
    @RequestMapping("/partners_add")
    public String partnersAdd() {
        return PREFIX + "partners_add.html";
    }

    /**
     * 跳转到修改合作伙伴管理
     */
    @RequestMapping("/partners_update/{partnersId}")
    public String partnersUpdate(@PathVariable Integer partnersId, Model model) {
        Partners partners = partnersService.selectById(partnersId);
        model.addAttribute("item",partners);
        LogObjectHolder.me().set(partners);
        return PREFIX + "partners_edit.html";
    }

    /**
     * 获取合作伙伴管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String imgName) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("img_name",imgName);
        return partnersService.selectList(entityWrapper);
    }

    /**
     * 新增合作伙伴管理
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public Object add(@RequestParam("img_url") MultipartFile img_url, Partners partners) {
        if (img_url.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = img_url.getOriginalFilename();
        String filePath = "/home/installPackage/imgs/";
        File dest = new File(filePath + fileName);
        try {
            img_url.transferTo(dest);
            partners.setImgLink(filePath + fileName);
            partners.setDescribe(partners.getDescribe());
            partners.setLink(partners.getLink());
            partnersService.insert(partners);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除合作伙伴管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer partnersId) {
        partnersService.deleteById(partnersId);
        return SUCCESS_TIP;
    }

    /**
     * 修改合作伙伴管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Partners partners) {
        partnersService.updateById(partners);
        return SUCCESS_TIP;
    }

    /**
     * 合作伙伴管理详情
     */
    @RequestMapping(value = "/detail/{partnersId}")
    @ResponseBody
    public Object detail(@PathVariable("partnersId") Integer partnersId) {
        return partnersService.selectById(partnersId);
    }
}
