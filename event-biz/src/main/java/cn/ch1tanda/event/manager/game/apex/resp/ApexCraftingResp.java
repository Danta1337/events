package cn.ch1tanda.event.manager.game.apex.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Apex复制器制造查询响应对象
 */
@Data
public class ApexCraftingResp implements Serializable {
    private static final long serialVersionUID = 4341899560011918147L;

    /**
     * 包名
     */
    private String bundle;

    /**
     * 存在开始时间
     */
    private Integer start;

    /**
     * 存在结束时间
     */
    private Integer end;

    /**
     * 物品类型
     * @see cn.ch1tanda.event.manager.game.apex.constant.enums.BundleTypeEnum
     */
    private String bundleType;

    /**
     * 制造物品内容
     */
    private List<BundleContent> bundleContent;

    @Data
    public static class BundleContent {

        /**
         * 项目名
         */
        private String item;

        /**
         * 制造所需点数
         */
        private Integer cost;

        /**
         * 项目类型
         */
        private ItemType itemType;
    }

    @Data
    public static class ItemType {

        /**
         * 物品名称
         */
        private String name;

        /**
         * 稀有物 ?
         */
        private String rarity;

        /**
         * 物品图片地址
         */
        private String asset;

        /**
         * 颜色 ?
         */
        private String rarityHex;
    }
}
