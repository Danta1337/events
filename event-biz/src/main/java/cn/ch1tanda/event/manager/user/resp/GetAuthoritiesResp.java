package cn.ch1tanda.event.manager.user.resp;

import cn.ch1tanda.event.convention.response.DefaultResult;
import cn.ch1tanda.event.convention.response.Result;

import java.util.List;

public class GetAuthoritiesResp extends DefaultResult<List<String>> {
    public GetAuthoritiesResp(String code, String msg, List<String> payload) {
        super(code, msg);
        setData(payload);
    }
}
