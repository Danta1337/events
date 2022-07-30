package cn.ch1tanda.event.model;

import lombok.Data;

/**
 * 请求历史记录数据对象
 */
@Data
public class RequestHistoryDO extends ConstraintDO {

    /**
     * IP地址
     */
    private String ip;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 网络服务提供商ISP
     */
    private String ISP;
}
