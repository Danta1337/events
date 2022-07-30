package cn.ch1tanda.event.test.mapper;

import cn.ch1tanda.event.mapper.RequestHistoryMapper;
import cn.ch1tanda.event.model.RequestHistoryDO;
import cn.ch1tanda.event.test.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class RequestHistoryMapperTest extends BaseTest {

    @Resource
    private RequestHistoryMapper requestHistoryMapper;

    @Test
    public void insertTest() {
        RequestHistoryDO requestHistoryDO = new RequestHistoryDO();
        requestHistoryDO.setIp("testIp");
        requestHistoryDO.setProvince("testProvince");
        requestHistoryDO.setCity("testCity");
        requestHistoryDO.setISP("testISP");
        requestHistoryDO.setCountry("testCountry");

        int insert = requestHistoryMapper.insert(requestHistoryDO);
        assert insert == 1;
    }
}
