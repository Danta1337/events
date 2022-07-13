package cn.ch1tanda.event.mapper;

import cn.ch1tanda.event.mapper.dataobject.ConfigDO;
import cn.ch1tanda.event.mapper.dataobject.query.ConfigQuery;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ConfigMapper {

    Boolean insert(ConfigDO item);

    ConfigDO selectByPkId(@Param("pkId") Integer pkId);

    String selectConfigValueByConfigTypeAndConfigKey(@Param("configType") String configType, @Param("configKey") String configKey);

    List<ConfigDO> selectAllConfigKeyAndConfigValueByConfigType(@Param("configType") String configType);

    List<ConfigDO> fullQuery(ConfigQuery query);

    List<ConfigDO> pageQuery(ConfigQuery query);
}
