package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.CaseDemo;
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
 * @since 2019-05-07
 */
public interface CaseDemoMapper extends BaseMapper<CaseDemo> {

    List<Map<String, Object>> selectLists(@Param("serviceId") String serviceId);
}
