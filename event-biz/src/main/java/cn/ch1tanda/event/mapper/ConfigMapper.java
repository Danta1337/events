package cn.ch1tanda.event.mapper;

import cn.ch1tanda.event.dataobject.ConfigDO;
import cn.ch1tanda.event.dataobject.query.ConfigQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMapper {

    Boolean insert (ConfigDO item);

    ConfigDO selectByPkId (@Param("pkId") Integer pkId);

    List<ConfigDO> fullQuery (ConfigQuery query);

    List<ConfigDO> pageQuery (ConfigQuery query);
}
