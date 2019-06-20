package cn.stylefeng.guns.modular.ledInfo.service;

import cn.stylefeng.guns.modular.system.model.LedInfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxw
 * @since 2019-05-22
 */
public interface ILedInfoService extends IService<LedInfo> {
    /**
     * 启用图片
     */
    void updateImgByType();

    void updateVideoByType();

    List<LedInfo> selectImgLink(Map<Object, Object> map);

    List<LedInfo> selectVido(Map<Object, Object> map);

    //更改图片/视频显示状态
    void updateState();

    //根据类型查询状态
    Integer selectStateByType(Integer type);

    List<Map<String, Object>> selectByState();

    List<String> selectByType(Integer type);

    Integer selectByUse();
}
