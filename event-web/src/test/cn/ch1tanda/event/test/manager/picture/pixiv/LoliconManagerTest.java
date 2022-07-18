package cn.ch1tanda.event.test.manager.picture.pixiv;

import cn.ch1tanda.event.manager.picture.pixiv.LoliconManager;
import cn.ch1tanda.event.manager.picture.pixiv.req.RandomPictureReq;
import cn.ch1tanda.event.manager.picture.pixiv.resp.RandomPictureResp;
import cn.ch1tanda.event.test.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class LoliconManagerTest extends BaseTest {

    @Resource
    private LoliconManager loliconManager;

    @Test
    public void getRandomPictureTest() {
        RandomPictureReq randomPictureReq = new RandomPictureReq();
        randomPictureReq.setNum(20);
        RandomPictureResp randomPicture = loliconManager.getRandomPicture(randomPictureReq);
        System.out.println(JSONObject.toJSONString(randomPicture));
    }
}
