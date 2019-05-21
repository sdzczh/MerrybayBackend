package cn.stylefeng.guns.modular.friendship_link.service;

import cn.stylefeng.guns.modular.system.model.FriendshipLink;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxw
 * @since 2019-05-09
 */
public interface IFriendshipLinkService extends IService<FriendshipLink> {

    void inserts(FriendshipLink friendshipLink);
}
