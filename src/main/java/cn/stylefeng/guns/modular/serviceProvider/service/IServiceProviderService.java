package cn.stylefeng.guns.modular.serviceProvider.service;

import cn.stylefeng.guns.modular.system.model.ServiceProvider;
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
public interface IServiceProviderService extends IService<ServiceProvider> {

    List<Map<String, Object>> selectLists();
}
