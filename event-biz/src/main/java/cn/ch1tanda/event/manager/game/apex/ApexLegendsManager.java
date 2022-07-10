package cn.ch1tanda.event.manager.game.apex;

import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexUIDQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.*;

import java.util.List;

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

    /**
     * 查询APEX猎杀人数与猎杀最低排位分等信息
     */
    ApexPredatorResp queryApexPredator();

    /**
     * 查询复制器制造轮换数据
     */
    List<ApexCraftingResp> queryApexCrafting();
}
