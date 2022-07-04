package cn.ch1tanda.event.dataobject;

import lombok.Data;

@Data
public class ConfigDO extends ConstraintDO{

    /**
     * 配置业务类型
     */
    private String configType;

    /**
     * 配置键
     */
    private String configKey;

    /**
     * 配置值
     */
    private String configValue;
}
