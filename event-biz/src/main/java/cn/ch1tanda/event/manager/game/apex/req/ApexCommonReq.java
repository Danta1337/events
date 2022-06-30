package cn.ch1tanda.event.manager.game.apex.req;

import cn.ch1tanda.event.utils.http.annotation.HttpParam;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApexCommonReq implements Serializable {
    private static final long serialVersionUID = -6433580088138899380L;

    @HttpParam
    private String auth;
}
