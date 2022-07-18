package cn.ch1tanda.event.manager.picture.pixiv;

import cn.ch1tanda.event.manager.picture.pixiv.req.RandomPictureReq;
import cn.ch1tanda.event.manager.picture.pixiv.resp.RandomPictureResp;

/**
 * 获取随机好康的
 * <a href='https://api.lolicon.app'>接口地址</a>
 */
public interface LoliconManager {

    /**
     * 获取随机图片
     */
    RandomPictureResp getRandomPicture(RandomPictureReq request);
}
