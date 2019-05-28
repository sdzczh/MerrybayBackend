package cn.stylefeng.guns.modular.news.controller;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.core.common.FileUploadUtils;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.system.model.CaseDemo;
import cn.stylefeng.guns.modular.system.warpper.NewsWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.News;
import cn.stylefeng.guns.modular.news.service.INewsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 新闻动态管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-07 14:13:05
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    private String PREFIX = "/news/news/";

    @Autowired
    private INewsService newsService;



    /**
     * 跳转到新闻动态管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "news.html";
    }

    /**
     * 跳转到添加新闻动态管理
     */
    @RequestMapping("/news_add")
    public String newsAdd() {
        return PREFIX + "news_add.html";
    }

    /**
     * 跳转到查看文章详情
     */
    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        News news = newsService.selectById(id);
        model.addAttribute("item",news);
        LogObjectHolder.me().set(news);
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
     * 跳转到修改新闻动态管理
     */
    @RequestMapping("/news_update/{newsId}")
    public String newsUpdate(@PathVariable Integer newsId, Model model) {
        News news = newsService.selectById(newsId);
        model.addAttribute("item",news);
        LogObjectHolder.me().set(news);
        return PREFIX + "news_edit.html";
    }

    /**
     * 获取新闻动态管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer news) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if(news != null) {
            entityWrapper.eq("type", news);
        }
        entityWrapper.orderBy("id", false);
        Page<News> page = new PageFactory<News>().defaultPage();
        List<Map<String, Object>> list = newsService.selectMaps(entityWrapper);
        page.setRecords(new NewsWarpper(list).wrap());
        return new PageInfoBT<>(page);

    }

    /**
     * 新增新闻动态管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(News news, String content ) {
        content = content.replaceAll("& ","&");
        news.setDetails(content);
        newsService.insert(news);
        return SUCCESS_TIP;
    }

    /**
     * 删除新闻动态管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer newsId) {
        newsService.deleteById(newsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改新闻动态管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(News news, String content ) {
        content = content.replaceAll("& ","&");
        news.setDetails(content);
        newsService.updateById(news);
        return SUCCESS_TIP;
    }

    /**
     * 新闻动态管理详情
     */
    @RequestMapping(value = "/detail/{newsId}")
    @ResponseBody
    public Object detail(@PathVariable("newsId") Integer newsId) {
        return newsService.selectById(newsId);
    }
}
