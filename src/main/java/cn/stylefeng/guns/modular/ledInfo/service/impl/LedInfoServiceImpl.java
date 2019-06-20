package cn.stylefeng.guns.modular.ledInfo.service.impl;

import cn.stylefeng.guns.modular.system.model.LedInfo;
import cn.stylefeng.guns.modular.system.dao.LedInfoMapper;
import cn.stylefeng.guns.modular.ledInfo.service.ILedInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxw
 * @since 2019-05-22
 */
@Service
public class LedInfoServiceImpl extends ServiceImpl<LedInfoMapper, LedInfo> implements ILedInfoService {
    @Resource
    private LedInfoMapper ledInfoMapper;

    @Override
    public void updateImgByType() {
        ledInfoMapper.updateImgByType();
    }

    @Override
    public void updateVideoByType() {
        ledInfoMapper.updateVideoByType();
    }

    @Override
    public List<LedInfo> selectImgLink(Map<Object, Object> map) {
        return ledInfoMapper.selectImgLink(map);
    }

    @Override
    public List<LedInfo> selectVido(Map<Object, Object> map) {
        return ledInfoMapper.selectVido(map);
    }

    @Override
    public void updateState() {
        ledInfoMapper.updateState();
    }

    @Override
    public Integer selectStateByType(Integer type) {
        return ledInfoMapper.selectStateByType(type);
    }

    @Override
    public List<Map<String, Object>> selectByState() {
        return ledInfoMapper.selectByState();
    }

    @Override
    public List<String> selectByType(Integer type) {
        return ledInfoMapper.selectByType(type);
    }

    @Override
    public Integer selectByUse() {
        return ledInfoMapper.selectByUse();
    }
}
