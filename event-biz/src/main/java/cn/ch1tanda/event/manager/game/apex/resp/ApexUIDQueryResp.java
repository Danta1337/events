package cn.ch1tanda.event.manager.game.apex.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApexUIDQueryResp extends ApexErrorResp implements Serializable {
    private static final long serialVersionUID = -4873396843841941190L;

    /**
     * 玩家姓名
     */
    private String name;

    /**
     * 玩家UID
     */
    private String uid;

    /**
     * 玩家PID
     */
    private String pid;

    /**
     * 头像URL
     */
    private String avatar;
}
