package cn.ch1tanda.event.manager.game.apex.req;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.utils.http.annotation.HttpParam;
import cn.ch1tanda.event.utils.variable.StringUtils;
import lombok.Data;

import java.util.Date;

@Data
public class ApexGameQueryReq extends ApexCommonReq{
    private static final long serialVersionUID = -5469290137801309006L;

    /**
     * 玩家UID，必填项
     */
    @HttpParam
    private String uid;

    /**
     * 游戏模式，只返回给定的游戏模式
     * BATTLE_ROYALE,ARENAS or UNKNOWN
     */
    @HttpParam
    private String mode;

    /**
     * 查询开始时间
     */
    @HttpParam
    private Date start;

    /**
     * 查询结束时间
     */
    @HttpParam
    private Date end;

    /**
     * 查询条数
     */
    @HttpParam
    private Integer limit;

    public void check () {
        if (StringUtils.isBlank(this.uid)) {
            throw new ServiceInvalidException("玩家UID不可为空！");
        }
    }
}
