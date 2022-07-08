package cn.ch1tanda.event.mapper.dataobject;

import lombok.Data;

import java.util.Date;

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

    public static ConfigDO build(String configType, String configKey, String configValue) {
        ConfigDO config = new ConfigDO();
        Date now = new Date();
        config.setGmtCreated(now);
        config.setGmtModified(now);
        config.setConfigType(configType);
        config.setConfigKey(configKey);
        config.setConfigValue(configValue);
        return config;
    }
}
