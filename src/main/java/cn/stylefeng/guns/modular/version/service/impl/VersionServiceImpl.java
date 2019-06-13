package cn.stylefeng.guns.modular.version.service.impl;

import cn.stylefeng.guns.modular.system.model.Version;
import cn.stylefeng.guns.modular.system.dao.VersionMapper;
import cn.stylefeng.guns.modular.version.service.IVersionService;
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
 * @author zhaohe
 * @since 2019-06-13
 */
@Service
public class VersionServiceImpl extends ServiceImpl<VersionMapper, Version> implements IVersionService {

    @Autowired
    private VersionMapper versionMapper;
    @Override
    public List<Map<String, Object>> selectLists() {
        EntityWrapper entityWrapper = new EntityWrapper();
        return versionMapper.selectMaps(entityWrapper);
    }
}
