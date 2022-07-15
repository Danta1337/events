package cn.ch1tanda.event.manager.user.resp;

import cn.ch1tanda.event.convention.response.DefaultResult;

public class RegisterResp extends DefaultResult<Void> {
    public RegisterResp(String code, String msg) {
        super(code, msg);
    }
}
