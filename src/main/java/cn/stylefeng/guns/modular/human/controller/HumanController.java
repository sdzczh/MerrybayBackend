package cn.stylefeng.guns.modular.human.controller;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.modular.system.warpper.HumanWarpper;
import cn.stylefeng.guns.modular.system.warpper.JobsWarpper;
import cn.stylefeng.guns.modular.system.warpper.NewsWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Human;
import cn.stylefeng.guns.modular.human.service.IHumanService;

import java.util.List;
import java.util.Map;

/**
 * 人力资源管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 14:19:28
 */
@Controller
@RequestMapping("/human")
public class HumanController extends BaseController {

    private String PREFIX = "/human/human/";

    @Autowired
    private IHumanService humanService;

    /**
     * 跳转到人力资源管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "human.html";
    }

    /**
     * 跳转到添加人力资源管理
     */
    @RequestMapping("/human_add")
    public String humanAdd() {
        return PREFIX + "human_add.html";
    }

    /**
     * 跳转到修改人力资源管理
     */
    @RequestMapping("/human_update/{humanId}")
    public String humanUpdate(@PathVariable Integer humanId, Model model) {
        Human human = humanService.selectById(humanId);
        model.addAttribute("item",human);
        LogObjectHolder.me().set(human);
        return PREFIX + "human_edit.html";
    }

    /**
     * 获取人力资源管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String category) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!StrUtil.isEmpty(category)){
            entityWrapper.eq("category",category);
        }
        List<Map<String, Object>> list = humanService.selectMaps(entityWrapper);
        return new HumanWarpper(list).wrap();
    }

    /**
     * 新增人力资源管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Human human) {
/*        if("".equals(human.getCategory()) || human.getCategory() == null){
            return "类型不能为空";
        }*/
        humanService.insert(human);
        return SUCCESS_TIP;
    }

    /**
     * 删除人力资源管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer humanId) {
        humanService.deleteById(humanId);
        return SUCCESS_TIP;
    }

    /**
     * 修改人力资源管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Human human) {
        humanService.updateById(human);
        return SUCCESS_TIP;
    }

    /**
     * 人力资源管理详情
     */
    @RequestMapping(value = "/detail/{humanId}")
    @ResponseBody
    public Object detail(@PathVariable("humanId") Integer humanId) {
        return humanService.selectById(humanId);
    }
}
