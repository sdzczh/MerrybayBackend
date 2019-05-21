package cn.stylefeng.guns.modular.human.service.impl;

import cn.stylefeng.guns.modular.system.model.Human;
import cn.stylefeng.guns.modular.system.dao.HumanMapper;
import cn.stylefeng.guns.modular.human.service.IHumanService;
import cn.stylefeng.guns.modular.system.warpper.JobsWarpper;
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
public class HumanServiceImpl extends ServiceImpl<HumanMapper, Human> implements IHumanService {

    @Autowired
    private HumanMapper humanMapper;
    @Override
    public List<Map<String, Object>> selectLists() {
        EntityWrapper entityWrapper = new EntityWrapper();
        return humanMapper.selectMaps(entityWrapper);
    }
}
