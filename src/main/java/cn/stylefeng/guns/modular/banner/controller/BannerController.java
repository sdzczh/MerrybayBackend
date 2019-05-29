package cn.stylefeng.guns.modular.banner.controller;

import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.core.common.DateUtils;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.system.model.News;
import cn.stylefeng.guns.modular.system.warpper.ImgTypeWarpper;
import cn.stylefeng.guns.modular.system.warpper.NewsWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Banner;
import cn.stylefeng.guns.modular.banner.service.IBannerService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页展示控制器
 *
 * @author fengshuonan
 * @Date 2019-05-06 17:33:42
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

    private String PREFIX = "/banner/banner/";

    @Autowired
    private IBannerService bannerService;

    /**
     * 跳转到首页展示首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "banner.html";
    }

    /**
     * 跳转到添加首页展示
     */
    @RequestMapping("/banner_add")
    public String bannerAdd() {
        return PREFIX + "banner_add.html";
    }

    /**
     * 跳转到修改首页展示
     */
    @RequestMapping("/banner_update/{bannerId}")
    public String bannerUpdate(@PathVariable Integer bannerId, Model model) {
        Banner banner = bannerService.selectById(bannerId);
        model.addAttribute("item",banner);
        LogObjectHolder.me().set(banner);
        return PREFIX + "banner_edit.html";
    }

    /**
     * 获取首页展示列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer aa) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if(aa != null) {
            entityWrapper.eq("aa", aa);
        }
        entityWrapper.orderBy("id", false);
        Page<Banner> page = new PageFactory<Banner>().defaultPage();
        List<Map<String, Object>> list = bannerService.selectMaps(entityWrapper);
        page.setRecords(new ImgTypeWarpper(list).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增首页展示
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public Object add(@RequestParam("img_url") MultipartFile img_url, Banner banner) {
        if (img_url.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = img_url.getOriginalFilename();
        /*String filePath = "/home/installPackage/imgs/";*/
        String filePath = "C:/Users/12778/Desktop/img/";
        File dest = new File(filePath + fileName);
        try {
            String saveUrl = "http://merrybay.tlhe.cn/caseDemo/showImg?imgUrl=";
            img_url.transferTo(dest);
            banner.setImgLink(saveUrl + fileName);
            bannerService.insert(banner);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除首页展示
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bannerId) {
        bannerService.deleteById(bannerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改首页展示
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Banner banner) {
        bannerService.updateById(banner);
        return SUCCESS_TIP;
    }

    /**
     * 首页展示详情
     */
    @RequestMapping(value = "/detail/{bannerId}")
    @ResponseBody
    public Object detail(@PathVariable("bannerId") Integer bannerId) {
        return bannerService.selectById(bannerId);
    }
}
