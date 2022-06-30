package cn.ch1tanda.event.manager.game.apex;

import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexUIDQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPlayerStatisticsQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexUIDQueryResp;

public interface ApexLegendsManager {

    /**
     * 查询地图轮换信息
     */
    ApexMapQueryResp queryMapRotationInfo (ApexMapQueryReq request);

    /**
     * 查询玩家统计信息
     */
    ApexPlayerStatisticsQueryResp queryPlayerStatisticsInfo (ApexPlayerStatisticsQueryReq request);

    /**
     * 根据玩家名称和平台查询玩家UID
     */
    ApexUIDQueryResp queryUIDByNameAndPlatform (ApexUIDQueryReq request);
}
