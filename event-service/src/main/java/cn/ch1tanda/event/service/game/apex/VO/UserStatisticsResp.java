package cn.ch1tanda.event.service.game.apex.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserStatisticsResp implements Serializable {
    private static final long serialVersionUID = -5837366057894467656L;

    private String name;

    private String level;

    private Integer toNextLevelPercent;


}
