package cn.ch1tanda.event.service.game.apex.impl;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.manager.framework.RedisManager;
import cn.ch1tanda.event.manager.game.apex.ApexLegendsManager;
import cn.ch1tanda.event.manager.game.apex.constant.ApexConstant;
import cn.ch1tanda.event.manager.game.apex.constant.enums.ApexPlatformEnum;
import cn.ch1tanda.event.manager.game.apex.req.ApexMapQueryReq;
import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.manager.game.apex.resp.ApexCraftingResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexMapQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPlayerStatisticsQueryResp;
import cn.ch1tanda.event.service.game.apex.ApexLegendsService;
import cn.ch1tanda.event.utils.exception.AssertUtils;
import cn.ch1tanda.event.utils.variable.DateUtils;
import cn.ch1tanda.event.utils.variable.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ApexLegendsLegendsServiceImpl implements ApexLegendsService {

    @Resource
    private ApexLegendsManager apexLegendsManager;

    @Resource
    private RedisManager redisManager;

    @Override
    public ApexMapQueryResp getMapRotationInfo() {
        ApexMapQueryResp mapRotationResponse;
        String cachedRotationInfo = redisManager.get(ApexConstant.APEX_MAP_ROTATION_REDIS_KEY);
        if (StringUtils.isBlank(cachedRotationInfo)) {
            mapRotationResponse = apexLegendsManager.queryMapRotationInfo(new ApexMapQueryReq());
            this.handleTimestamp(mapRotationResponse);
            redisManager.set(ApexConstant.APEX_MAP_ROTATION_REDIS_KEY
                    , JSONObject.toJSONString(mapRotationResponse)
                    , this.getMapRotationRedisExpirationTime(mapRotationResponse));
        } else {
            mapRotationResponse = JSONObject.parseObject(cachedRotationInfo, ApexMapQueryResp.class);
        }
        return mapRotationResponse;
    }

    @Override
    public List<ApexCraftingResp.BundleContent> getDailyOrWeeklyCraftingContent() {
        String cachedCraftingContent = redisManager.get(ApexConstant.APEX_CRAFTING_REDIS_KEY);
        List<ApexCraftingResp.BundleContent> result;
        if (StringUtils.isBlank(cachedCraftingContent)) {
            result = new ArrayList<>();
            List<ApexCraftingResp> apexCraftingResp = apexLegendsManager.queryApexCrafting();
            // 只获取每周或每日更新的复制器物品
            List<ApexCraftingResp> dailyOrWeeklyCrafting = apexCraftingResp.stream()
                    .filter(item -> item.getBundleType().equals("daily") || item.getBundleType()
                            .equals("weekly")).collect(Collectors.toList());
            dailyOrWeeklyCrafting.forEach(item -> result.addAll(item.getBundleContent()));
            redisManager.set(ApexConstant.APEX_CRAFTING_REDIS_KEY, JSONObject.toJSONString(result), this.getCraftingRedisExpirationTime(dailyOrWeeklyCrafting));
        } else {
            result = JSONArray.parseArray(cachedCraftingContent, ApexCraftingResp.BundleContent.class);
        }
        return result;
    }

    @Override
    public ApexPlayerStatisticsQueryResp.BasicInfo getPlayerStatisticsInfo(String userName, ApexPlatformEnum platform) {
        AssertUtils.isNotBlank(userName, "User name cannot be null!");
        AssertUtils.isTrue(Objects.nonNull(platform), "Platform cannot be null!");
        String cachedPlayerStatistics = redisManager.get(ApexConstant.APEX_QUERY_USER_RESULT_REDIS_KEY + userName + ":" + platform.getCode());
        ApexPlayerStatisticsQueryResp.BasicInfo result;
        if (StringUtils.isBlank(cachedPlayerStatistics)) {
            ApexPlayerStatisticsQueryReq request = new ApexPlayerStatisticsQueryReq();
            request.setPlayer(userName);
            request.setPlatform(platform.getCode());
            ApexPlayerStatisticsQueryResp queryResponse = apexLegendsManager.queryPlayerStatisticsInfo(request);
            if (StringUtils.isNotBlank(queryResponse.getError())) {
                throw new ServiceInvalidException(queryResponse.getError());
            }
            result = queryResponse.getGlobal();
            // 默认缓存20分钟
            redisManager.set(ApexConstant.APEX_QUERY_USER_RESULT_REDIS_KEY + userName + ":" + platform.getCode(), JSONObject.toJSONString(result), 1000 * 60 * 20L);
        } else {
            result = JSONObject.parseObject(cachedPlayerStatistics, ApexPlayerStatisticsQueryResp.BasicInfo.class);
        }
        return result;
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

    /**
     * 获取所有模式当前轮换地图中最短的剩余时间（ms）
     */
    private long getMapRotationRedisExpirationTime(ApexMapQueryResp mapRotation) {
        try {
            List<ApexMapQueryResp.MapRotationDetails> rotationDetails = new ArrayList<>();
            rotationDetails.addAll(mapRotation.getBattleRoyale().values());
            rotationDetails.addAll(mapRotation.getArenas().values());
            rotationDetails.addAll(mapRotation.getRanked().values());
            rotationDetails.addAll(mapRotation.getArenasRanked().values());
            int min = Integer.MAX_VALUE;
            for (ApexMapQueryResp.MapRotationDetails item : rotationDetails) {
                if (Objects.nonNull(item.getRemainingSecs()) && item.getRemainingSecs() < min) {
                    min = item.getRemainingSecs();
                }
            }
            return new BigDecimal(String.valueOf(min)).multiply(new BigDecimal("1000")).longValue();
        } catch (Exception e) {
            log.info("计算地图轮换缓存时间时异常", e);
            return 0L;
        }
    }

    private long getCraftingRedisExpirationTime(List<ApexCraftingResp> crafting) {
        try {
            List<Integer> endTimeList = crafting.stream().map(ApexCraftingResp::getEnd).collect(Collectors.toList());
            int min = Integer.MAX_VALUE;
            for(Integer item : endTimeList) {
                if (item < min) {
                    min = item;
                }
            }
            return new BigDecimal(String.valueOf(min)).multiply(new BigDecimal("1000")).subtract(new BigDecimal(String.valueOf(System.currentTimeMillis()))).longValue();
        } catch (Exception e) {
            log.info("计算复制器制造物品列表缓存时间时异常", e);
            return 0L;
        }
    }
}
