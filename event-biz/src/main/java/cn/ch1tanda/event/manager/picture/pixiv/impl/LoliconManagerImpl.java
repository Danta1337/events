package cn.ch1tanda.event.manager.picture.pixiv.impl;

import cn.ch1tanda.event.manager.picture.pixiv.LoliconManager;
import cn.ch1tanda.event.manager.picture.pixiv.constant.LoliconConstant;
import cn.ch1tanda.event.manager.picture.pixiv.req.RandomPictureReq;
import cn.ch1tanda.event.manager.picture.pixiv.resp.RandomPictureResp;
import cn.ch1tanda.event.utils.http.HttpUtils;
import org.springframework.stereotype.Component;

@Component
public class LoliconManagerImpl implements LoliconManager {

    @Override
    public RandomPictureResp getRandomPicture(RandomPictureReq request) {
        return HttpUtils.POST(LoliconConstant.LOLICON_API_URL, request, RandomPictureResp.class);
    }
}
