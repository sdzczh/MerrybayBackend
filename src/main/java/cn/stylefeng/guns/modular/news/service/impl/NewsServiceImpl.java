package cn.stylefeng.guns.modular.news.service.impl;

import cn.stylefeng.guns.modular.system.model.News;
import cn.stylefeng.guns.modular.system.dao.NewsMapper;
import cn.stylefeng.guns.modular.news.service.INewsService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxw
 * @since 2019-05-07
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Autowired
    private NewsMapper newsMapper;
    @Override
    public List<Map<String, Object>> selectLists() {
        EntityWrapper entityWrapper = new EntityWrapper();

        return newsMapper.selectMaps(entityWrapper);
    }
}
