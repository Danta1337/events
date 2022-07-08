package cn.ch1tanda.event.manager.tools.config;

/**
 * 数据库配置服务
 */
public interface ConfigManager {

    /**
     * 根据configType与configKey获取配置
     * @param type 配置类型，非必填，默认值为DEFAULT
     * @return 如果配置存在，则返回对应配置的configValue
     * <p>否则返回空字符串""</p>
     */
    String getConfigByTypeAndKey (String type, String key);

    Boolean addNewConfig (String type, String key, String value);
}
