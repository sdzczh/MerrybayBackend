package cn.stylefeng.guns.modular.human.service;

import cn.stylefeng.guns.modular.system.model.Human;
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
public interface IHumanService extends IService<Human> {

    List<Map<String, Object>> selectLists();
}
