package cn.ch1tanda.event.mapper;

import cn.ch1tanda.event.model.ConfigDO;
import cn.ch1tanda.event.model.query.ConfigQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMapper {

    Boolean insert(ConfigDO item);

    ConfigDO selectByPkId(@Param("pkId") Integer pkId);

    String selectConfigValueByConfigTypeAndConfigKey(@Param("configType") String configType, @Param("configKey") String configKey);

    List<ConfigDO> selectAllConfigKeyAndConfigValueByConfigType(@Param("configType") String configType);

    List<ConfigDO> fullQuery(ConfigQuery query);

    List<ConfigDO> pageQuery(ConfigQuery query);

    int updateConfigValueByConfigTypeAndConfigKey (@Param("configType") String type, @Param("configKey") String key, @Param("configValue") String value);
}
