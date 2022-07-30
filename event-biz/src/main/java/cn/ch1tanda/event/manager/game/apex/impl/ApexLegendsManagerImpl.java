package cn.ch1tanda.event.manager.game.apex.impl;

import cn.ch1tanda.event.manager.framework.RedisManager;
import cn.ch1tanda.event.manager.game.apex.ApexLegendsManager;
import cn.ch1tanda.event.manager.game.apex.constant.ApexConstant;
import cn.ch1tanda.event.manager.game.apex.constant.ApexRequestPath;
import cn.ch1tanda.event.manager.game.apex.req.ApexCommonReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexUIDQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.*;
import cn.ch1tanda.event.mapper.ConfigMapper;
import cn.ch1tanda.event.utils.http.HttpUtils;
import cn.ch1tanda.event.utils.variable.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class ApexLegendsManagerImpl implements ApexLegendsManager {

    @Resource
    private RedisManager redisManager;

    @Resource
    private ConfigMapper configMapper;

    public static final String REQUEST_URL = "https://api.mozambiquehe.re";

    @Override
    public ApexMapQueryResp queryMapRotationInfo(ApexMapQueryReq request) {
        request.setAuth(this.getAuthAPIKey());
        return HttpUtils.GETJson(REQUEST_URL + ApexRequestPath.MAP_ROTATION + HttpUtils.getHttpParamStr(request)
                , ApexMapQueryResp.class);
    }

    @Override
    public ApexPlayerStatisticsQueryResp queryPlayerStatisticsInfo(ApexPlayerStatisticsQueryReq request) {
        request.setAuth(this.getAuthAPIKey());
        request.checkParam();
        if (StringUtils.isNotBlank(request.getPlayer()) && StringUtils.isNotBlank(request.getUid())) {
            // 如果同时传了Name和UID，默认取UID
            request.setPlayer(null);
        }
        return HttpUtils.GETJson(REQUEST_URL + ApexRequestPath.QUERY_PLAY_STATISTICS + HttpUtils.getHttpParamStr(request)
                , ApexPlayerStatisticsQueryResp.class);
    }

    @Override
    public ApexUIDQueryResp queryUIDByNameAndPlatform(ApexUIDQueryReq request) {
        request.setAuth(this.getAuthAPIKey());
        request.checkParam();
        return HttpUtils.GETJson(REQUEST_URL + ApexRequestPath.QUERY_UID_BY_NAME + HttpUtils.getHttpParamStr(request)
                , ApexUIDQueryResp.class);
    }

    @Override
    public ApexPredatorResp queryApexPredator() {
        ApexCommonReq request = new ApexCommonReq();
        request.setAuth(this.getAuthAPIKey());
        return HttpUtils.GETJson(REQUEST_URL + ApexRequestPath.PREDATOR + HttpUtils.getHttpParamStr(request)
                , ApexPredatorResp.class);
    }

    @Override
    public List<ApexCraftingResp> queryApexCrafting() {
        ApexCommonReq request = new ApexCommonReq();
        request.setAuth(this.getAuthAPIKey());
        return HttpUtils.GETJsonArray(REQUEST_URL + ApexRequestPath.CRAFTING + HttpUtils.getHttpParamStr(request)
                , ApexCraftingResp.class);
    }

    /**
     * 获取调用第三方平台的APIkey
     */
    private String getAuthAPIKey () {
        if (StringUtils.isBlank(ApexConstant.APEX_API_KEY)) {
            ApexConstant.APEX_API_KEY = configMapper.selectConfigValueByConfigTypeAndConfigKey(ApexConstant.APEX_CONFIG_TYPE, ApexConstant.APEX_API_KEY_CONFIG_KEY);
        }
        return ApexConstant.APEX_API_KEY;
    }
}
