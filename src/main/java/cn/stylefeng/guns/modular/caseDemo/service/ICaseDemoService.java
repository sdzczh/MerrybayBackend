package cn.stylefeng.guns.modular.caseDemo.service;

import cn.stylefeng.guns.modular.system.model.CaseDemo;
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
public interface ICaseDemoService extends IService<CaseDemo> {

    List<Map<String, Object>> selectLists(String serviceId);
}
