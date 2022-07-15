package cn.ch1tanda.event.manager.user;

import cn.ch1tanda.event.manager.user.req.AuthReq;
import cn.ch1tanda.event.manager.user.req.GetAuthoritiesReq;
import cn.ch1tanda.event.manager.user.req.RegisterReq;
import cn.ch1tanda.event.manager.user.resp.AuthResp;
import cn.ch1tanda.event.manager.user.resp.GetAuthoritiesResp;
import cn.ch1tanda.event.manager.user.resp.RegisterResp;

public interface UserManager {

    /**
     *
     * @param req
     * @return
     */
    AuthResp auth(AuthReq req);

    RegisterResp register(RegisterReq req);

    GetAuthoritiesResp getAuthorities(GetAuthoritiesReq req);
}
