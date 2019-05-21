package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.FriendshipLink;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxw
 * @since 2019-05-09
 */
public interface FriendshipLinkMapper extends BaseMapper<FriendshipLink> {

    void inserts(FriendshipLink friendshipLink);
}
