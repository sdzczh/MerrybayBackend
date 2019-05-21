package cn.stylefeng.guns.modular.serviceProvider.service.impl;

import cn.stylefeng.guns.modular.system.model.ServiceProvider;
import cn.stylefeng.guns.modular.system.dao.ServiceProviderMapper;
import cn.stylefeng.guns.modular.serviceProvider.service.IServiceProviderService;
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
public class ServiceProviderServiceImpl extends ServiceImpl<ServiceProviderMapper, ServiceProvider> implements IServiceProviderService {

    @Autowired
    private ServiceProviderMapper serviceProviderMapper;
    @Override
    public List<Map<String, Object>> selectLists() {
        EntityWrapper entityWrapper = new EntityWrapper();
        return serviceProviderMapper.selectMaps(entityWrapper);
    }
}
