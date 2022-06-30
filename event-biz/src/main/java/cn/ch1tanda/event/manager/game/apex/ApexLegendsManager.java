package cn.ch1tanda.event.manager.game.apex;

import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;

public interface ApexLegendsManager {

    ApexMapQueryResp queryMapRotation (ApexMapQueryReq request);
}
