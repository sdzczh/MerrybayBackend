package cn.stylefeng.guns.modular.ledInfo.controller;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.modular.system.warpper.HumanWarpper;
import cn.stylefeng.guns.modular.system.warpper.LedInfoWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.LedInfo;
import cn.stylefeng.guns.modular.ledInfo.service.ILedInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * LED管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-22 10:53:58
 */
@Controller
@RequestMapping("/ledInfo")
public class LedInfoController extends BaseController {

    private String PREFIX = "/ledInfo/ledInfo/";

    @Autowired
    private ILedInfoService ledInfoService;

    /**
     * 跳转到LED管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ledInfo.html";
    }

    /**
     * 跳转到添加LED管理
     */
    @RequestMapping("/ledInfo_add")
    public String ledInfoAdd() {
        return PREFIX + "ledInfo_add.html";
    }
    /**
     * 跳转到添加LED管理
     */
    @RequestMapping("/ledInfo_add_video")
    public String ledInfo_add_video() {
        return PREFIX + "ledInfo_addvideo.html";
    }

    /**
     * 跳转到修改LED管理
     */
    @RequestMapping("/ledInfo_update/{ledInfoId}")
    public String ledInfoUpdate(@PathVariable Integer ledInfoId, Model model) {
        LedInfo ledInfo = ledInfoService.selectById(ledInfoId);
        model.addAttribute("item",ledInfo);
        LogObjectHolder.me().set(ledInfo);
        return PREFIX + "ledInfo_edit.html";
    }

    /**
     * 获取LED管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String type) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!StrUtil.isEmpty(type)){
            entityWrapper.eq("type",type);
        }
        List<Map<String, Object>> list = ledInfoService.selectMaps(entityWrapper);
        return new LedInfoWarpper(list).wrap();
    }

    /**
     * 新增LED视频管理
     */
    @RequestMapping(value = "/addvideo")
    @ResponseBody
    public Object addvideo(LedInfo ledInfo) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("type", 1);
        //查找是否有视频存在
        Integer videoCount = ledInfoService.selectCount(wrapper);
        if(videoCount != 0){
           return "false";
        }
        Integer state = ledInfoService.selectStateByType(0);
        if(state == null){
            state = 0;
        }
        ledInfo.setState(1 - state);
        ledInfoService.insert(ledInfo);
        return SUCCESS_TIP;
    }


    /**
     * 新增LED管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestParam("img_url") MultipartFile img_url, LedInfo ledInfo) {
        if(img_url.isEmpty()){
            return "文件不能为空";
        }
        String fileName = img_url.getOriginalFilename();
        String filePath = "/home/installPackage/imgs/";
        /*String filePath = "C:/Users/12778/Desktop/img/";*/
        File dest = new File(filePath + fileName);
        try {
            String saveUrl = "http://merrybay.tlhe.cn/caseDemo/showImg?imgUrl=";
            img_url.transferTo(dest);
            Integer state = ledInfoService.selectStateByType(1);
            if(state == null){
                state = 0;
            }
            ledInfo.setState(1 - state);
            ledInfo.setImgLink(saveUrl + fileName);
            ledInfoService.insert(ledInfo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除LED管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ledInfoId) {
        ledInfoService.deleteById(ledInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改LED管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LedInfo ledInfo) {
        ledInfoService.updateById(ledInfo);
        return SUCCESS_TIP;
    }

    /**
     * LED管理详情
     */
    @RequestMapping(value = "/detail/{ledInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("ledInfoId") Integer ledInfoId) {
        return ledInfoService.selectById(ledInfoId);
    }

    /**
     * 修改状态
     */
    @RequestMapping(value = "/updateState")
    @ResponseBody
    public Object updateByType() {
        ledInfoService.updateState();
        return SUCCESS_TIP;
    }
    /**
     * 显示返回图片或视频数据
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public String get() {
        List<Map<String, Object>> list = ledInfoService.selectByState();
        return JSONObject.toJSONString(list);
    }
}
