package cn.stylefeng.guns.modular.version.service;

import cn.stylefeng.guns.modular.system.model.Version;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaohe
 * @since 2019-06-13
 */
public interface IVersionService extends IService<Version> {

    List<Map<String, Object>> selectLists();
}
