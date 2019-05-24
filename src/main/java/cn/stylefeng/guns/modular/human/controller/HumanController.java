package cn.stylefeng.guns.modular.human.controller;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.core.common.FileUploadUtils;
import cn.stylefeng.guns.modular.system.warpper.HumanWarpper;
import cn.stylefeng.guns.modular.system.warpper.JobsWarpper;
import cn.stylefeng.guns.modular.system.warpper.NewsWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Human;
import cn.stylefeng.guns.modular.human.service.IHumanService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * 富文本工具上传图片
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response){
        try {
            return FileUploadUtils.fileUpload(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/fileManager", method = RequestMethod.GET)
    public static void fileManager(HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        FileUploadUtils.fileManager(request,response);
    }
    /**
     *author:zhaohe
     * IO流读取图片
     * @param imgUrl 图片url
     */
    @RequestMapping(value = "/showImg",method = RequestMethod.GET)
    public void ioReadImage(String imgUrl, HttpServletResponse response)throws IOException {
        FileUploadUtils.ioReadImage(imgUrl,response);
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
    public Object add(Human human, String content, String jobsDuty ) {
        content = content.replaceAll("& ","&");
        human.setJobsRequire(content);
        jobsDuty = jobsDuty.replaceAll("& ","&");
        human.setJobsDuty(jobsDuty);
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
    public Object update(Human human, String content, Integer id, String jobsDuty) {
        human.setId(id);
        content = content.replaceAll("& ","&");
        human.setJobsRequire(content);
        jobsDuty = jobsDuty.replaceAll("& ","&");
        human.setJobsDuty(jobsDuty);
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
