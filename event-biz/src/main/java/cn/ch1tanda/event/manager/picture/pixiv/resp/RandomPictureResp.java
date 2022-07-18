package cn.ch1tanda.event.manager.picture.pixiv.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RandomPictureResp implements Serializable {
    private static final long serialVersionUID = 3654432534771469181L;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 图片数据
     */
    private List<Picture> data;

    @Data
    public static class Picture implements Serializable{
        private static final long serialVersionUID = 140364917397474239L;

        /**
         * 作品pid
         */
        private String pid;

        /**
         * 作品所在页
         */
        @JSONField(name = "p")
        private String page;

        /**
         * 作者uid
         */
        private String uid;

        /**
         * 作品标题
         */
        private String title;

        /**
         * 作者名称
         */
        private String author;

        /**
         * 是否 R18（在库中的分类，不等同于作品本身的 R18 标识）
         */
        private Boolean r18;

        /**
         * 原图宽度 px
         */
        private Integer width;

        /**
         * 原图高度 px
         */
        private Integer height;

        /**
         * 作品标签，包含标签的中文翻译（有的话）
         */
        private List<String> tags;

        /**
         * 图片扩展名
         */
        private String ext;

        /**
         * 图片上传日期
         */
        private Date uploadDate;

        /**
         * 包含了所有指定size的图片地址
         */
        private URLWithDifferentSize urls;
    }

    /**
     * @see cn.ch1tanda.event.manager.picture.pixiv.constant.enums.SizeEnum
     */
    @Data
    public static class URLWithDifferentSize implements Serializable {
        private static final long serialVersionUID = -2102073750603859198L;

        private String original;

        private String regular;

        private String small;

        private String thumb;

        private String mini;
    }
}
