package cn.ch1tanda.event.manager.picture.pixiv.req;

import cn.ch1tanda.event.manager.picture.pixiv.constant.enums.SizeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class RandomPictureReq implements Serializable {
    private static final long serialVersionUID = 5036510658585233507L;

    /**
     * 0为非 R18，1为 R18，2为混合（在库中的分类，不等同于作品本身的 R18 标识）
     * @see cn.ch1tanda.event.manager.picture.pixiv.constant.enums.R18Enum
     */
    private Integer r18;

    /**
     * 一次返回的结果数量，范围1～20
     */
    private Integer num;

    /**
     * 返回制定uid作者的作品，最多20个
     */
    private List<Integer> uid;

    /**
     * 返回从标题、作者、标签中按指定关键字模糊匹配的结果，大小写不敏感，性能和准度较差且功能单一，建议使用tag代替
     */
    private String keyword;

    /**
     * 返回从标题匹配制定标签的作品
     * 参数数组内的每个字符串之间应用 AND 规则（最多3个）；每个字符串可以是若干个由|分隔的标签，它们之间应用 OR 规则（最多20个）
     * 举个例子，我需要查找“(萝莉或少女)的(白丝或黑丝)的色图”，即 (萝莉 OR 少女) AND (白丝 OR 黑丝)，那么可以这样发送请求
     * "tag": ["萝莉|少女", "白丝|黑丝"]
     */
    private List<String> tag;

    /**
     * 返回指定图片规格的地址
     * 默认为"original"
     * @see cn.ch1tanda.event.manager.picture.pixiv.constant.enums.SizeEnum
     */
    private List<String> size = Collections.singletonList(SizeEnum.ORIGINAL.getCode());

    /**
     * 在线反代服务
     * 默认为"i.pixiv.re"
     */
    private String proxy = "i.pixiv.re";

    /**
     * 返回该时间以后上传的作品
     */
    private Date dateAfter;

    /**
     * 返回该时间以前上传的作品
     */
    private Date dateBefore;

    /**
     * 设置为任意真值以禁用对某些缩写keyword和tag的自动转换，比如一些简称：
     * vtb => 虚拟YouTuber|VTuber
     * fgo => Fate/GrandOrder|Fate/Grand Order|FateGrandOrder
     */
    private Boolean dsc;
}
