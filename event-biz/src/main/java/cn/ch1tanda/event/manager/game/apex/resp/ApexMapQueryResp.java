package cn.ch1tanda.event.manager.game.apex.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ApexMapQueryResp extends ApexErrorResp implements Serializable {
    private static final long serialVersionUID = -7265679765858045725L;

    /**
     * 大逃杀模式
     */
    private Map<String, MapRotationDetails> battleRoyale;

    /**
     * 竞技场模式
     */
    private Map<String, MapRotationDetails> arenas;

    /**
     * 排位模式
     */
    private Map<String, MapRotationDetails> ranked;

    /**
     * 竞技场排位模式
     */
    private Map<String, MapRotationDetails> arenasRanked;

    /**
     * 控制模式
     */
    private Map<String, MapRotationDetails> control;

    @Data
    public static class MapRotationDetails {
        /**
         * 轮换开始时间
         * 秒级别时间戳
         */
        private Long start;

        /**
         * 轮换结束时间
         * 秒级别时间戳
         */
        private Long end;

        /**
         * 轮换地图名称
         */
        private String map;

        /**
         * 轮换地图编码
         */
        private String code;

        /**
         * 持续时间（秒）
         */
        @JSONField(name = "DurationInSecs")
        private Integer durationInSecs;

        /**
         * 持续时间（分）
         */
        @JSONField(name = "DurationInMinutes")
        private Integer durationInMinutes;

        /**
         * 资产（地图图片地址）
         */
        private String asset;

        /**
         * 剩余时间（秒）
         */
        private Integer remainingSecs;

        /**
         * 剩余时间（分）
         */
        private Integer remainingMins;

        /**
         * 剩余时间（HH:mm:ss）
         */
        private String remainingTimer;
    }
}
