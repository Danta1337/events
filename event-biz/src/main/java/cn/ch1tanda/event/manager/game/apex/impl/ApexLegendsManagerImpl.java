package cn.ch1tanda.event.manager.game.apex.impl;

import cn.ch1tanda.event.manager.game.apex.ApexLegendsManager;
import cn.ch1tanda.event.manager.game.apex.constant.ApexRequestPath;
import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;
import cn.ch1tanda.event.utils.http.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ApexLegendsManagerImpl implements ApexLegendsManager {

    public static final String API_KEY = "516e0c73e87944cbce73fe20ca073997";

    public static final String REQUEST_URL = "https://api.mozambiquehe.re";
    @Override
    public ApexMapQueryResp queryMapRotation(ApexMapQueryReq request) {
        return HttpUtils.GET(REQUEST_URL + ApexRequestPath.MAP_ROTATION + HttpUtils.getHttpParamStr(request)
                , ApexMapQueryResp.class);
    }


    public static void main(String[] args) {
        ApexLegendsManagerImpl i = new ApexLegendsManagerImpl();
        ApexMapQueryReq req = new ApexMapQueryReq();
        req.setAuth(API_KEY);
        req.setVersion("2");
        String httpParamStr = HttpUtils.getHttpParamStr(req);
        System.out.println(httpParamStr);
        ApexMapQueryResp apexMapQueryResp = i.queryMapRotation(req);
        System.out.println(JSONObject.toJSONString(apexMapQueryResp));
    }
}
