package cn.stylefeng.guns.modular.version.controller;

import cn.stylefeng.guns.core.common.Result;
import cn.stylefeng.guns.core.common.ResultCode;
import cn.stylefeng.guns.modular.system.warpper.VersionWarpper;
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
import cn.stylefeng.guns.modular.system.model.Version;
import cn.stylefeng.guns.modular.version.service.IVersionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版本控制控制器
 *
 * @author fengshuonan
 * @Date 2019-06-13 15:29:28
 */
@Controller
@RequestMapping("/version")
public class VersionController extends BaseController {

    private String PREFIX = "/version/version/";

    @Autowired
    private IVersionService versionService;

    /**
     * 跳转到版本控制首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "version.html";
    }

    /**
     * 跳转到添加版本控制
     */
    @RequestMapping("/version_add")
    public String versionAdd() {
        return PREFIX + "version_add.html";
    }

    /**
     * 跳转到修改版本控制
     */
    @RequestMapping("/version_update/{versionId}")
    public String versionUpdate(@PathVariable Integer versionId, Model model) {
        Version version = versionService.selectById(versionId);
        model.addAttribute("item",version);
        LogObjectHolder.me().set(version);
        return PREFIX + "version_edit.html";
    }

    /**
     * 获取版本控制列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String,Object>> list = versionService.selectLists();
        return new VersionWarpper(list).wrap();
    }

    /**
     * 新增版本控制
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Version version) {
        versionService.insert(version);
        return SUCCESS_TIP;
    }

    /**
     * 删除版本控制
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer versionId) {
        versionService.deleteById(versionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改版本控制
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Version version) {
        versionService.updateById(version);
        return SUCCESS_TIP;
    }

    /**
     * 版本控制详情
     */
    @RequestMapping(value = "/detail/{versionId}")
    @ResponseBody
    public Object detail(@PathVariable("versionId") Integer versionId) {
        return versionService.selectById(versionId);
    }

    /**
     * 显示返回图片或视频数据
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public Object get(Integer updateVersion) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.gt("update_version", updateVersion);
        List<Map<String, Object>> list = versionService.selectList(entityWrapper);
        Map<String, Object> map = new HashMap<>();
        if(list.size() == 0){
            map.put("data", new HashMap<>());
            map.put("update_flag", false);
            return Result.toResult2(ResultCode.SUCCESS, map);
        }else{
            entityWrapper = new EntityWrapper();
            entityWrapper.eq("update_flag", 1);
            map.put("update_flag", true);
            list = versionService.selectList(entityWrapper);
            map.put("data", list.get(0));
            return Result.toResult2(ResultCode.SUCCESS, map);
        }
    }
}
