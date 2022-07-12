package cn.ch1tanda.event.service.config.impl;

import cn.ch1tanda.event.service.config.ConfigService;
import cn.ch1tanda.event.service.config.constant.enums.ConfigTypeEnum;
import cn.ch1tanda.event.mapper.ConfigMapper;
import cn.ch1tanda.event.mapper.dataobject.ConfigDO;
import cn.ch1tanda.event.mapper.dataobject.query.ConfigQuery;
import cn.ch1tanda.event.utils.exception.AssertUtils;
import cn.ch1tanda.event.utils.variable.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConfigServiceImpl implements ConfigService {

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

    @Override
    public Map<String, String> getConfigMapByConfigType(String type) {
        AssertUtils.isNotBlank(type, "配置类型不可为空！");
        ConfigQuery query = new ConfigQuery();
        query.setConfigType(type);
        List<ConfigDO> configs = configMapper.fullQuery(query);
        if (configs.isEmpty()) {
            return new HashMap<>();
        }
        return configs.stream().collect(
                Collectors.toMap(ConfigDO::getConfigKey
                        , ConfigDO::getConfigValue
                        , (oldVal, newVal) -> newVal));
    }
}
