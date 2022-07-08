package cn.ch1tanda.event.manager.tools.config.impl;

import cn.ch1tanda.event.manager.tools.config.ConfigManager;
import cn.ch1tanda.event.manager.tools.config.constant.enums.ConfigTypeEnum;
import cn.ch1tanda.event.mapper.ConfigMapper;
import cn.ch1tanda.event.mapper.dataobject.ConfigDO;
import cn.ch1tanda.event.utils.exception.AssertUtils;
import cn.ch1tanda.event.utils.variable.StringUtils;

import javax.annotation.Resource;

public class ConfigManagerImpl implements ConfigManager {

    @Resource
    private ConfigMapper configMapper;

    @Override
    public String getConfigValueByTypeAndKey(String type, String key) {
        AssertUtils.isNotBlank(key, "配置key不可为空！");
        if (StringUtils.isBlank(type)) {
            type = ConfigTypeEnum.DEFAULT.getCode();
        }
        String value = configMapper.selectConfigValueByConfigTypeAndConfigKey(type, key);
        return StringUtils.isBlank(value) ? "": value;
    }

    @Override
    public Boolean addNewConfig(String type, String key, String value) {
        AssertUtils.isNotBlank(key, "配置key不可为空！");
        AssertUtils.isNotBlank(value, "配置value不可为空！");
        if (StringUtils.isBlank(type)) {
            type = ConfigTypeEnum.DEFAULT.getCode();
        }
        ConfigDO newConfig = ConfigDO.build(type, key, value);
        return configMapper.insert(newConfig);
    }
}
