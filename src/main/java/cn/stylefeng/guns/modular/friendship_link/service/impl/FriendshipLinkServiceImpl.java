package cn.stylefeng.guns.modular.friendship_link.service.impl;

import cn.stylefeng.guns.modular.system.model.FriendshipLink;
import cn.stylefeng.guns.modular.system.dao.FriendshipLinkMapper;
import cn.stylefeng.guns.modular.friendship_link.service.IFriendshipLinkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxw
 * @since 2019-05-09
 */
@Service
public class FriendshipLinkServiceImpl extends ServiceImpl<FriendshipLinkMapper, FriendshipLink> implements IFriendshipLinkService {

    @Autowired
    FriendshipLinkMapper friendshipLinkMapper;
    @Override
    public void inserts(FriendshipLink friendshipLink) {
        friendshipLinkMapper.inserts(friendshipLink);
    }
}
