package cn.ch1tanda.event.manager.game.apex.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApexErrorResp implements Serializable {
    private static final long serialVersionUID = -4572400366341605771L;

    @JSONField(name = "Error")
    private String error;
}
