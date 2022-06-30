package cn.ch1tanda.event.manager.game.apex.req;

import cn.ch1tanda.event.utils.http.annotation.HttpParam;
import lombok.Data;

@Data
public class ApexMapQueryReq extends ApexCommonReq {
    private static final long serialVersionUID = 8980749899387258831L;

    /**
     * required = false
     * 1 for battle royale pubs only, 2 for all modes.
     * if use, please use 2 because the response json object can not handle the condition of 2
     */
    @HttpParam
    private String version;
}
