package cn.ch1tanda.event.manager.game.apex.impl;

import cn.ch1tanda.event.manager.game.apex.ApexLegendsManager;
import cn.ch1tanda.event.manager.game.apex.constant.ApexRequestPath;
import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexUIDQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.ApexErrorResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPlayerStatisticsQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexUIDQueryResp;
import cn.ch1tanda.event.utils.http.HttpUtils;
import cn.ch1tanda.event.utils.http.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ApexLegendsManagerImpl implements ApexLegendsManager {

    public static final String API_KEY = "516e0c73e87944cbce73fe20ca073997";

    public static final String REQUEST_URL = "https://api.mozambiquehe.re";
    @Override
    public ApexMapQueryResp queryMapRotation(ApexMapQueryReq request) {
        request.setAuth(API_KEY);
        return HttpUtils.GET(REQUEST_URL + ApexRequestPath.MAP_ROTATION + HttpUtils.getHttpParamStr(request)
                , ApexMapQueryResp.class);
    }

    @Override
    public ApexPlayerStatisticsQueryResp queryPlayerStatisticsInfo(ApexPlayerStatisticsQueryReq request) {
        request.setAuth(API_KEY);
        request.checkParam();
        if (StringUtils.isNotBlank(request.getPlayer()) && StringUtils.isNotBlank(request.getUid())) {
            // 如果同时传了playName和playUID，默认取PlayerUID
            request.setPlayer(null);
        }
        return HttpUtils.GET(REQUEST_URL + ApexRequestPath.QUERY_PLAY_STATISTICS + HttpUtils.getHttpParamStr(request)
                , ApexPlayerStatisticsQueryResp.class);
    }

    @Override
    public ApexUIDQueryResp queryUIDByNameAndPlatform(ApexUIDQueryReq request) {
        request.setAuth(API_KEY);
        request.checkParam();
        return HttpUtils.GET(REQUEST_URL + ApexRequestPath.QUERY_UID_BY_NAME + HttpUtils.getHttpParamStr(request)
                , ApexUIDQueryResp.class);
    }


    public static void main(String[] args) {
        ApexLegendsManagerImpl i = new ApexLegendsManagerImpl();
        ApexUIDQueryReq req = new ApexUIDQueryReq();
        req.setPlatform("PC");
        req.setName("dududubobobo");
        ApexUIDQueryResp apexUIDQueryResp = i.queryUIDByNameAndPlatform(req);
        System.out.println(apexUIDQueryResp);
        System.out.println(apexUIDQueryResp.getError());
    }
}
