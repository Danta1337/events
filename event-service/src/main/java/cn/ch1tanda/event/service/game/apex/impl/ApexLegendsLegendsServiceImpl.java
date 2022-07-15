package cn.ch1tanda.event.service.game.apex.impl;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.manager.game.apex.ApexLegendsManager;
import cn.ch1tanda.event.manager.game.apex.constant.enums.PlatformEnum;
import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.ApexCraftingResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPlayerStatisticsQueryResp;
import cn.ch1tanda.event.service.game.apex.ApexLegendsService;
import cn.ch1tanda.event.utils.exception.AssertUtils;
import cn.ch1tanda.event.utils.variable.DateUtils;
import cn.ch1tanda.event.utils.variable.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ApexLegendsLegendsServiceImpl implements ApexLegendsService {

    @Resource
    private ApexLegendsManager apexLegendsManager;

    @Override
    public ApexMapQueryResp getMapRotationInfo() {
        ApexMapQueryResp mapRotationResponse = apexLegendsManager.queryMapRotationInfo(new ApexMapQueryReq());
        this.handleTimestamp(mapRotationResponse);
        return mapRotationResponse;
    }

    @Override
    public List<ApexCraftingResp.BundleContent> getDailyOrWeeklyCraftingContent() {
        List<ApexCraftingResp.BundleContent> result = new ArrayList<>();
        List<ApexCraftingResp> apexCraftingResp = apexLegendsManager.queryApexCrafting();
        // 只获取每周或每日更新的复制器物品
        List<ApexCraftingResp> dailyOrWeeklyCrafting = apexCraftingResp.stream()
                .filter(item -> item.getBundleType().equals("daily") || item.getBundleType()
                        .equals("weekly")).collect(Collectors.toList());
        dailyOrWeeklyCrafting.forEach(item -> result.addAll(item.getBundleContent()));
        return result;
    }

    @Override
    public ApexPlayerStatisticsQueryResp.BasicInfo getPlayerStatisticsInfo(String userName, PlatformEnum platform) {
        AssertUtils.isNotBlank(userName, "User name cannot be null!");
        AssertUtils.isTrue(Objects.nonNull(platform), "Platform cannot be null!");
        ApexPlayerStatisticsQueryReq request = new ApexPlayerStatisticsQueryReq();
        request.setPlayer(userName);
        request.setPlatform(platform.getCode());
        ApexPlayerStatisticsQueryResp queryResponse = apexLegendsManager.queryPlayerStatisticsInfo(request);
        if (StringUtils.isNotBlank(queryResponse.getError())) {
            throw new ServiceInvalidException(queryResponse.getError());
        }
        return queryResponse.getGlobal();
    }

    /**
     * 由于Apex第三方接口返回的时间戳格式为秒级别，FastJson会自动以毫秒级别处理，所以需要进行转换
     */
    private void handleTimestamp (ApexMapQueryResp response) {
        if (Objects.nonNull(response.getBattleRoyale())) {
            response.setBattleRoyale(this.timestampHandling(response.getBattleRoyale()));
        }
        if (Objects.nonNull(response.getArenas())) {
            response.setArenas(this.timestampHandling(response.getArenas()));
        }
        if (Objects.nonNull(response.getRanked())) {
            response.setRanked(this.timestampHandling(response.getRanked()));
        }
        if (Objects.nonNull(response.getArenasRanked())) {
            response.setArenasRanked(this.timestampHandling(response.getArenasRanked()));
        }
        if (Objects.nonNull(response.getControl())) {
            response.setControl(this.timestampHandling(response.getControl()));
        }
    }

    private Map<String, ApexMapQueryResp.MapRotationDetails>  timestampHandling (Map<String, ApexMapQueryResp.MapRotationDetails> map) {
        Map<String, ApexMapQueryResp.MapRotationDetails> result = new HashMap<>();
        for (Map.Entry<String, ApexMapQueryResp.MapRotationDetails> entry : map.entrySet()) {
            ApexMapQueryResp.MapRotationDetails value = entry.getValue();
            value.setStart(DateUtils.secTimestampToMSTimestamp(value.getStart()));
            value.setEnd(DateUtils.secTimestampToMSTimestamp(value.getEnd()));
            result.put(entry.getKey(), value);
        }
        return result;
    }
}
