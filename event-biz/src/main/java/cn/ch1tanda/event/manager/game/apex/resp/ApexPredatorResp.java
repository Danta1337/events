package cn.ch1tanda.event.manager.game.apex.resp;

import cn.ch1tanda.event.manager.game.apex.constant.enums.ApexPlatformEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 查询APEX抵达猎杀所需分数响应参数
 */
@Data
public class ApexPredatorResp implements Serializable {
    private static final long serialVersionUID = 3512480111509869326L;

    /**
     * 大逃杀排位赛
     * Map的key即平台platformCode
     * @see ApexPlatformEnum
     */
    private Map<String, ApexPredatorItem> RP;

    /**
     * 竞技场排位赛
     * Map的key即平台platformCode
     * @see ApexPlatformEnum
     */
    private Map<String, ApexPredatorItem> AP;


    /**
     * 抵达猎杀所需分数项
     */
    @Data
    public static class ApexPredatorItem {

        /**
         * 段位中先有的猎杀人数
         */
        private Integer foundRank;

        /**
         * 猎杀最低分数
         */
        private String val;

        /**
         * 猎杀最低分用户UID
         */
        private String uid;

        /**
         * 更新时间戳
         */
        private Date updateTimestamp;

        /**
         * 猎杀与大师段位人数总数
         */
        private Integer totalMastersAndPreds;
    }
}
