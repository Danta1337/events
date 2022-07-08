package cn.ch1tanda.event.manager.game.apex.req;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.utils.http.annotation.HttpParam;
import cn.ch1tanda.event.utils.variable.StringUtils;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApexUIDQueryReq extends ApexCommonReq implements Serializable {
    private static final long serialVersionUID = -478366749053703053L;

    /**
     * 玩家名称
     */
    @HttpParam
    private String name;

    /**
     * 游戏平台
     */
    @HttpParam
    private String platform;

    public void checkParam() {
        if (StringUtils.isBlank(this.name)) {
            throw new ServiceInvalidException("游戏名称不可为空！");
        }
        if (StringUtils.isBlank(this.platform)) {
            throw new ServiceInvalidException("游戏平台不可为空！");
        }
    }
}
