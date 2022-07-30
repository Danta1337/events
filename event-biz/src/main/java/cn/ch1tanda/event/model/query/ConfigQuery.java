package cn.ch1tanda.event.model.query;

import cn.ch1tanda.event.model.ConfigDO;
import lombok.Data;

/**
 * 配置查询对象，继承于ConfigDO
 * 可以自定义额外的查询参数，例如分页相关的参数
 */
@Data
public class ConfigQuery extends ConfigDO {

    /**
     * 每页数据量
     */
    private Integer offset = 10;

    /**
     * 起始查询位置
     */
    private Integer startPos = 0;
}
