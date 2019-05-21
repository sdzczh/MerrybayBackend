package cn.stylefeng.guns.modular.news.service;

import cn.stylefeng.guns.modular.system.model.News;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxw
 * @since 2019-05-07
 */
public interface INewsService extends IService<News> {
    List<Map<String, Object>> selectLists();
}
