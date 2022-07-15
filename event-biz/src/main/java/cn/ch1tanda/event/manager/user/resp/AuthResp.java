package cn.ch1tanda.event.manager.user.resp;

import cn.ch1tanda.event.convention.response.DefaultResult;
import cn.ch1tanda.event.model.User;

public class AuthResp extends DefaultResult<User> {
    public AuthResp(String code, String msg, User payload) {
        super(code, msg);
        setData(payload);
    }
}
