package cn.ch1tanda.event.manager.game.apex.req;

import cn.ch1tanda.event.utils.http.annotation.HttpParam;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApexQueryByNameReq extends ApexCommonReq implements Serializable {
    private static final long serialVersionUID = 8016645360647782952L;

    @HttpParam
    private String player;

    @HttpParam
    private String platform;
}
