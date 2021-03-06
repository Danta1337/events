package cn.ch1tanda.event.service.game.apex;

import cn.ch1tanda.event.manager.game.apex.constant.enums.ApexPlatformEnum;
import cn.ch1tanda.event.manager.game.apex.resp.ApexCraftingResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPlayerStatisticsQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPredatorResp;

import java.util.List;

public interface ApexLegendsService {

    /**
     * 获取地图轮换信息
     */
    ApexMapQueryResp getMapRotationInfo ();

    /**
     * 获取复制器每日或每周一换的物品信息
     */
    List<ApexCraftingResp.BundleContent> getDailyOrWeeklyCraftingContent();

    /**
     * 获取用户统计信息
     */
    ApexPlayerStatisticsQueryResp.BasicInfo getPlayerStatisticsInfo (String userName, ApexPlatformEnum platform);

    /**
     * 获取猎杀与大师段位统计信息
     */
    ApexPredatorResp getApexPredatorInfo ();
}
