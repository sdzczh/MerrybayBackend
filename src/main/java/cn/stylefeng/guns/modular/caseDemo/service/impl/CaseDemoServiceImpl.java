package cn.stylefeng.guns.modular.caseDemo.service.impl;

import cn.stylefeng.guns.modular.system.model.CaseDemo;
import cn.stylefeng.guns.modular.system.dao.CaseDemoMapper;
import cn.stylefeng.guns.modular.caseDemo.service.ICaseDemoService;
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
public class CaseDemoServiceImpl extends ServiceImpl<CaseDemoMapper, CaseDemo> implements ICaseDemoService {

    @Autowired
    private CaseDemoMapper caseDemoMapper;
    @Override
    public List<Map<String, Object>> selectLists(String serviceId) {
        return caseDemoMapper.selectLists(serviceId);
    }
}
