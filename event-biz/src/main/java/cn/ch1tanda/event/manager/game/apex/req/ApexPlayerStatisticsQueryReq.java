package cn.ch1tanda.event.manager.game.apex.req;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.utils.http.StringUtils;
import cn.ch1tanda.event.utils.http.annotation.HttpParam;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApexPlayerStatisticsQueryReq extends ApexCommonReq implements Serializable {
    private static final long serialVersionUID = 8016645360647782952L;

    /**
     * 玩家名称
     */
    @HttpParam
    private String player;

    /**
     * 玩家UID
     */
    @HttpParam
    private String uid;

    /**
     * 平台
     * @see cn.ch1tanda.event.manager.game.apex.constant.enums.PlatformEnum
     */
    @HttpParam
    private String platform;

    // --------------------------------以下参数非必传--------------------------------

    /**
     * API版本号：1、2、4、5
     * 当前默认为5，不建议使用其他版本
     */
    @HttpParam
    private String version;

    /**
     * 开启获取玩家工会，目前还在测试中
     */
    @HttpParam
    private Boolean enableClubsBeta;

    /**
     * 跳过排位赛排名数据
     * 可以填写任何数值
     */
    @HttpParam
    private String skipRank;

    /**
     * 合并相同类型跟踪器值
     * 可以填写任何数值
     */
    @HttpParam
    private String merge;

    /**
     * 删除已合并的跟踪器，仅保留合并后的值
     * 可以填写任何数值
     */
    @HttpParam
    private String removeMerged;

    public void checkParam () {
        if (StringUtils.isBlank(this.player) && StringUtils.isBlank(this.uid)) {
            throw new ServiceInvalidException("玩家名称与UID不可同时为空！");
        }
        if (StringUtils.isBlank(this.platform)) {
            throw new ServiceInvalidException("游戏平台不可为空！");
        }
    }
}
