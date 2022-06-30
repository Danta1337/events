package cn.ch1tanda.event.manager.game.apex.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApexPlayerStatisticsQueryResp extends ApexErrorResp implements Serializable {
    private static final long serialVersionUID = 3715817249098653841L;

    private BasicInfo global;

    @Data
    public static class BasicInfo {
        /**
         * 玩家名称
         */
        private String name;

        /**
         * 玩家UID
         */
        private String uid;

        /**
         * 化身?
         */
        private String avatar;

        /**
         * 平台
         * @see cn.ch1tanda.event.manager.game.apex.constant.enums.PlatformEnum
         */
        private String platform;

        /**
         * 游戏级别
         */
        private Integer level;

        /**
         * 到下一等级经验百分比
         */
        private Integer toNextLevelPercent;

        /**
         * 封禁信息
         */
        private BanInfo bans;

        /**
         * 排位赛信息
         */
        private RankInfo rank;

        /**
         * 竞技场排位信息
         */
        private RankInfo arena;

        /**
         * 徽章列表
         */
        private List<Badge> badges;

        @Data
        public static class BanInfo {
            private Boolean isActive;

            private String remainingSeconds;

            @JSONField(name = "lastBanReason")
            private String lastBanReason;
        }

        @Data
        public static class RankInfo {
            private Integer rankScore;

            private String rankName;

            private Integer randDiv;

            private Integer ladderPosPlatform;

            private String rankImg;

            private String rankedSeason;
        }

        @Data
        public static class Badge {
            private String name;

            private String value;
        }
    }

    @Data
    public static class Realtime {
        /**
         * 大厅状态
         */
        private String lobbyState;

        /**
         * 是否在线
         */
        private Integer isOnline;

        /**
         * 是否在游戏中
         */
        private Integer isInGame;

        /**
         * 是否可以加入
         */
        private Integer canJoin;

        /**
         * 队伍是否已满
         */
        private Integer partyFull;

        /**
         * 选中的传奇
         */
        private String selectedLegend;

        /**
         * 目前状态
         */
        private String currentState;

        /**
         * 目前状态开始的时间戳
         */
        private Integer currentStateSinceTimestamp;

        /**
         * 目前状态的文本值
         */
        private String currentStateAsText;
    }
}
