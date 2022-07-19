package cn.ch1tanda.event.manager.user.req;

import lombok.Getter;

public class GetAuthoritiesReq {
    @Getter
    private final String username;

    public GetAuthoritiesReq(String username) {
        this.username = username;
    }
}
