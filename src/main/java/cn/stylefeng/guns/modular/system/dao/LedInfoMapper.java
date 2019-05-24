package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.LedInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxw
 * @since 2019-05-22
 */
public interface LedInfoMapper extends BaseMapper<LedInfo> {

    void updateImgByType();

    void updateVideoByType();

    List<LedInfo> selectImgLink(Map<Object, Object> map);
    List<LedInfo> selectVido(Map<Object, Object> map);

    void updateState();

    Integer selectStateByType(@Param("type") Integer type);

    List<Map<String, Object>> selectByState();
}
