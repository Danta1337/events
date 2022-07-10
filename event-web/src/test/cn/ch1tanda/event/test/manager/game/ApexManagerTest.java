package cn.ch1tanda.event.test.manager.game;

import cn.ch1tanda.event.manager.game.apex.ApexLegendsManager;
import cn.ch1tanda.event.manager.game.apex.constant.enums.PlatformEnum;
import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexUIDQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.*;
import cn.ch1tanda.event.test.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

public class ApexManagerTest extends BaseTest {

    @Resource
    private ApexLegendsManager apexLegendsManager;

    @Test
    public void queryMapRotationTest() {
        ApexMapQueryReq request = new ApexMapQueryReq();
        request.setVersion("2");
        ApexMapQueryResp response = apexLegendsManager.queryMapRotationInfo(request);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void queryPlayerStatisticsInfoTest() {
        ApexPlayerStatisticsQueryReq request = new ApexPlayerStatisticsQueryReq();
        request.setPlatform(PlatformEnum.PC.getCode());
        request.setPlayer("wobush1bot");
        ApexPlayerStatisticsQueryResp response = apexLegendsManager.queryPlayerStatisticsInfo(request);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void queryUIDByNameAndPlatformTest() {
        ApexUIDQueryReq request = new ApexUIDQueryReq();
        request.setPlatform(PlatformEnum.PC.getCode());
        request.setName("wobush1bot");
        ApexUIDQueryResp response = apexLegendsManager.queryUIDByNameAndPlatform(request);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void queryApexPredatorTest() {
        ApexPredatorResp response = apexLegendsManager.queryApexPredator();
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void queryApexCraftingTest() {
        List<ApexCraftingResp> response = apexLegendsManager.queryApexCrafting();
        System.out.println(JSONObject.toJSONString(response));
    }
}
