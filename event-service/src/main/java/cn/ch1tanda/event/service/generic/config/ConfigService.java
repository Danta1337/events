package cn.ch1tanda.event.service.generic.config;

import java.util.Map;

/**
 * 数据库配置服务
 */
public interface ConfigService {

    /**
     * 根据configType与configKey获取配置
     * @param type 配置类型，非必填，默认值为DEFAULT
     * @return 如果配置存在，则返回对应配置的configValue
     * <p>否则返回空字符串""</p>
     */
    String getConfigValueByTypeAndKey(String type, String key);

    /**
     * 新增配置，如果配置存在，则根据type与key更新value
     * @param type 非必填，默认为default
     * @return 新增结果
     */
    Boolean setConfig(String type, String key, String value);

    /**
     * 根据配置类型获取该配置类型下所有的配置
     * @param type 配置类型，不可为空
     * @return key - configKey, value - configValue
     */
    Map<String, String> getConfigMapByConfigType (String type);
}
