package cn.stylefeng.guns.modular.caseDemo.controller;

import cn.stylefeng.guns.core.common.FileUploadUtils;
import cn.stylefeng.guns.modular.system.warpper.ProviderWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.CaseDemo;
import cn.stylefeng.guns.modular.caseDemo.service.ICaseDemoService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 案例管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 13:48:14
 */
@Controller
@RequestMapping("/caseDemo")
public class CaseDemoController extends BaseController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String PREFIX = "/caseDemo/caseDemo/";

    @Autowired
    private ICaseDemoService caseDemoService;

    /**
     * 跳转到案例管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "caseDemo.html";
    }

    /**
     * 跳转到添加案例管理
     */
    @RequestMapping("/caseDemo_add")
    public String caseDemoAdd() {
        return PREFIX + "caseDemo_add.html";
    }

    /**
     * 跳转到查看文章详情
     */
    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        CaseDemo caseDemo = caseDemoService.selectById(id);
        model.addAttribute("item",caseDemo);
        LogObjectHolder.me().set(caseDemo);
        return PREFIX + "show.html";
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
     * 跳转到修改案例管理
     */
    @RequestMapping("/caseDemo_update/{caseDemoId}")
    public String caseDemoUpdate(@PathVariable Integer caseDemoId, Model model) {
        CaseDemo caseDemo = caseDemoService.selectById(caseDemoId);
        model.addAttribute("item",caseDemo);
        LogObjectHolder.me().set(caseDemo);
        return PREFIX + "caseDemo_edit.html";
    }

    /**
     * 获取案例管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String serviceId) {
        List<Map<String, Object>> list = caseDemoService.selectLists(serviceId);
        return new ProviderWarpper(list).wrap();
    }

    /**
     * 新增案例管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CaseDemo caseDemo) {
        /*if(StringUtils.isEmpty(title)){
            return FileUploadUtils.getError("标题不能为空");
        }
        if (StringUtils.isEmpty(serviceId)){
            return FileUploadUtils.getError("案例类型不能为空");
        }
        if (StringUtils.isEmpty(describe)){
            return FileUploadUtils.getError("描述不能为空");
        }
        if (StringUtils.isEmpty(content)){
            return FileUploadUtils.getError("详情不能为空");
        }
        content = content.replaceAll("& ", "&");
        caseDemo.setTitle(title);
        caseDemo.setServiceId(Integer.parseInt(serviceId));
        caseDemo.setDescribe(describe);
        caseDemo.setImgLink(imgLink);
        caseDemo.setDetails(content);*/
        caseDemoService.insert(caseDemo);
        return SUCCESS_TIP;
    }

    /**
     * 删除案例管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer caseDemoId) {
        caseDemoService.deleteById(caseDemoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改案例管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CaseDemo caseDemo) {
        caseDemoService.updateById(caseDemo);
        return SUCCESS_TIP;
    }

    /**
     * 案例管理详情
     */
    @RequestMapping(value = "/detail/{caseDemoId}")
    @ResponseBody
    public Object detail(@PathVariable("caseDemoId") Integer caseDemoId) {
        return caseDemoService.selectById(caseDemoId);
    }

}
