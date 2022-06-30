package cn.ch1tanda.event.manager.game.apex;

import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPlayerStatisticsQueryResp;

public interface ApexLegendsManager {

    ApexMapQueryResp queryMapRotation (ApexMapQueryReq request);

    ApexPlayerStatisticsQueryResp queryPlayerStatisticsInfo (ApexPlayerStatisticsQueryReq request);
}
