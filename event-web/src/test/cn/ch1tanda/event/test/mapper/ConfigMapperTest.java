package cn.ch1tanda.event.test.mapper;

import cn.ch1tanda.event.mapper.dataobject.ConfigDO;
import cn.ch1tanda.event.mapper.dataobject.query.ConfigQuery;
import cn.ch1tanda.event.mapper.ConfigMapper;
import cn.ch1tanda.event.test.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


public class ConfigMapperTest extends BaseTest {

    @Resource
    ConfigMapper configMapper;

    @Test
    public void insertTest () {
        ConfigDO config = new ConfigDO();
        Date date = new Date();
        config.setGmtCreated(date);
        config.setGmtModified(date);
        config.setRemark("测试备注");
        config.setConfigType("Test");
        config.setConfigValue("testValue");
        config.setConfigKey("testKey");
        Boolean insert = configMapper.insert(config);
        System.out.println(insert);
    }

    @Test
    public void fullQueryTest () {
        ConfigQuery query = new ConfigQuery();
        query.setConfigType("Test");
        List<ConfigDO> configDOS = configMapper.fullQuery(query);
        System.out.println(JSONObject.toJSONString(configDOS));
    }

    @Test
    public void selectBytIdTest () {
        ConfigDO configDO = configMapper.selectByPkId(1);
        System.out.println(configDO);
    }

    @Test
    public void selectAllConfigKeyAndConfigValueByConfigTypeTest() {
        List<ConfigDO> game = configMapper.selectAllConfigKeyAndConfigValueByConfigType("FILE");
        System.out.println(JSONObject.toJSONString(game));
    }
}
